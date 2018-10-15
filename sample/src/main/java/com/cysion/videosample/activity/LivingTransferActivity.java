package com.cysion.videosample.activity;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.videosample.R;
import com.cysion.videosample.entity.TransferTextBean;
import com.cysion.videosample.util.EncryptUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import omrecorder.AudioChunk;
import omrecorder.AudioRecordConfig;
import omrecorder.OmRecorder;
import omrecorder.PullTransport;
import omrecorder.PullableSource;
import omrecorder.Recorder;
import omrecorder.WriteAction;

public class LivingTransferActivity extends BaseActivity {

    public static final int FREQUENCY = 16000;
    public static final int IDLE = 340;
    public static final int PLAYING = 341;
    public static final int PAUSING = 342;
    public static final int FINISHED = 343;

    // 每次发送的数据大小 1280 字节
    private static final int CHUNCKED_SIZE = 1280;
    // appid
    private static final String APPID = "5bbb1fd3";

    // appid对应的secret_key
    private static final String SECRET_KEY = "264bf5298b368f551147fd42493a3682";
    // 请求地址
    private static final String HOST = "rtasr.xfyun.cn/v1/ws";

    private static final String BASE_URL = "ws://" + HOST;

    private static final String ORIGIN = "http://" + HOST;


    Recorder recorder;
    ImageView recordButton;
    private String mResPath;
    private int mRecordState = IDLE;
    private ImageView mIvPlay;
    private ImageView mIvStop;
    private TextView mTvTarget;
    private WebSocket mWebSocket;
    private String mConfirmedText = "";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recorder;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(self);
        mResPath = getExternalCacheDir().getAbsolutePath() + "/pcmtest.pcm";
        setupRecorder();
        mIvPlay = findViewById(R.id.iv_play_or_pause);
        mTvTarget = (TextView) findViewById(R.id.tv_target);
        mIvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recorder == null) {
                    return;
                }
                if (mRecordState == PLAYING) {
                    recorder.pauseRecording();
                    mIvPlay.setImageResource(R.drawable.play);
                    mRecordState = PAUSING;
                    Toast.makeText(self, "暂停录音", Toast.LENGTH_SHORT).show();

                } else if (mRecordState == PAUSING) {
                    recorder.resumeRecording();
                    mIvPlay.setImageResource(R.drawable.pause);
                    Toast.makeText(self, "继续录音", Toast.LENGTH_SHORT).show();
                    mRecordState = PLAYING;

                } else {
                    mIvPlay.setImageResource(R.drawable.pause);
                    recorder.startRecording();
                    mRecordState = PLAYING;
                    connect();
                    Toast.makeText(self, "开始录音", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mIvStop = findViewById(R.id.iv_stop);
        mIvStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecord();
            }
        });
        recordButton = findViewById(R.id.recordButton);
    }

    private void stopRecord() {
        if (recorder == null) {
            return;
        }
        if (mRecordState == IDLE || mRecordState == FINISHED) {
            return;
        }
        try {
            recorder.stopRecording();
            mRecordState = FINISHED;
            mIvPlay.setImageResource(R.drawable.play);
            Toast.makeText(self, "录音完成，已保存至" + mResPath, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recordButton.post(new Runnable() {
            @Override
            public void run() {
                animateVoice(0);
            }
        });
    }


    private void setupRecorder() {
        recorder = OmRecorder.pcm(
                new PullTransport.Default(mic(), new PullTransport.OnAudioChunkPulledListener() {
                    @Override
                    public void onAudioChunkPulled(AudioChunk audioChunk) {
                        animateVoice((float) (audioChunk.maxAmplitude() / 200.0));
                    }
                }, new WriteAction() {
                    @Override
                    public void execute(AudioChunk audioChunk, OutputStream outputStream) throws IOException {
                        outputStream.write(audioChunk.toBytes());
                        sendLivingStream(audioChunk.toBytes());
                    }
                }), file());
    }


    private void animateVoice(final float maxPeak) {
        recordButton.animate().scaleX(1 + maxPeak).scaleY(1 + maxPeak).setDuration(10).start();
    }


    private PullableSource mic() {
        return new PullableSource.Default(
                new AudioRecordConfig.Default(
                        MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT,
                        AudioFormat.CHANNEL_IN_MONO, FREQUENCY
                )
        );
    }

    @NonNull
    private File file() {
        return new File(mResPath);
    }

    private void connect() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(BASE_URL + getHandShakeParams(APPID, SECRET_KEY)).build();
        LivingTransferActivity.EchoWebSocketListener socketListener = new LivingTransferActivity.EchoWebSocketListener();
        mOkHttpClient.newWebSocket(request, socketListener);
        mOkHttpClient.dispatcher().executorService().shutdown();
    }


    final class EchoWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            Log.e("flag--", "onOpen(WebSocketActivity.java:82)---->>" + Thread.currentThread().getName());
            Log.e("flag--", "onOpen(LivingTransferActivity.java:204)---->>" + "连接成功");
            mWebSocket = webSocket;
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            Log.e("flag--", "onMessage(WebSocketActivity.java:88)---->>" + Thread.currentThread().getName());
            output(text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            Log.e("flag--", "onMessage(WebSocketActivity.java:94)---->>" + Thread.currentThread().getName());
            output(bytes.hex());
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
            Log.e("flag--", "onClosing(WebSocketActivity.java:100)---->>" + Thread.currentThread().getName());
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            Log.e("flag--", "onClosed(WebSocketActivity.java:106)---->>" + Thread.currentThread().getName());
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            Log.e("flag--", "onFailure(WebSocketActivity.java:112)---->>" + Thread.currentThread().getName());
        }
    }

    private void output(String text) {
        final String transferedText = getContent(text);
        Log.e("flag--", "WebSocketActivity.output(WebSocketActivity.java:111)--" + transferedText);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTarget.setText(transferedText);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRecord();

        if (mWebSocket != null) {
            mWebSocket.cancel();
        }
    }

    private void sendLivingStream(byte[] aBytes) {
        if (mWebSocket != null) {
            mWebSocket.send(ByteString.of(aBytes));
        }
    }

    // 生成握手参数
    public static String getHandShakeParams(String appId, String secretKey) {
        String ts = System.currentTimeMillis() / 1000 + "";
        String signa = "";
        try {
            signa = EncryptUtil.HmacSHA1Encrypt(EncryptUtil.MD5(appId + ts), secretKey);
            return "?appid=" + appId + "&ts=" + ts + "&signa=" + URLEncoder.encode(signa, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 把转写结果解析为句子
    public String getContent(String message) {
        StringBuffer resultBuilder = new StringBuffer();
        message.replaceAll("\\\\", "");
        String transferingText = "";
        try {
            JSONObject messageObj = new JSONObject(message);
            TransferTextBean bean = new Gson().fromJson(messageObj.optString("data"), TransferTextBean.class);
            if (bean != null && bean.getCn() != null) {
                if (bean.getCn().getSt().getType().equals("0")) {
                    List<TransferTextBean.CnBean.StBean.RtBean> rt = bean.getCn().getSt().getRt();
                    TransferTextBean.CnBean.StBean.RtBean rtBean1 = rt.get(0);
                    List<TransferTextBean.CnBean.StBean.RtBean.WsBean> ws = rtBean1.getWs();
                    for (TransferTextBean.CnBean.StBean.RtBean.WsBean w : ws) {
                        resultBuilder.append(w.getCw().get(0).getW());
                    }
                    transferingText = resultBuilder.toString();
                    mConfirmedText = mConfirmedText + transferingText;
                    return mConfirmedText;

                } else {
                    List<TransferTextBean.CnBean.StBean.RtBean> rt = bean.getCn().getSt().getRt();
                    TransferTextBean.CnBean.StBean.RtBean rtBean1 = rt.get(0);
                    List<TransferTextBean.CnBean.StBean.RtBean.WsBean> ws = rtBean1.getWs();
                    for (TransferTextBean.CnBean.StBean.RtBean.WsBean w : ws) {
                        resultBuilder.append(w.getCw().get(0).getW());
                    }
                    transferingText = resultBuilder.toString();
                    return mConfirmedText + transferingText;
                }
            }
        } catch (Exception e) {
            return mConfirmedText;
        }
        return mConfirmedText;
    }
}
