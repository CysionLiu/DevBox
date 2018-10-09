package com.cysion.videosample.activity;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.videosample.R;
import com.cysion.videosample.util.EncryptUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_connect)
    Button mBtnConnect;
    @BindView(R.id.et_input_client)
    EditText mEtInputClient;
    @BindView(R.id.btn_send)
    Button mBtnSend;
    @BindView(R.id.btn_close)
    Button mBtnClose;
    @BindView(R.id.tv_show_msg)
    TextView mTvShowMsg;
    private WebSocket mWebSocket;

    // 每次发送的数据大小 1280 字节
    private static final int CHUNCKED_SIZE = 1280;
    // appid
    private static final String APPID = "5a698a26";

    // appid对应的secret_key
    private static final String SECRET_KEY = "59f937fe4601472b06c5b466f3c620d3";
    // 请求地址
    private static final String HOST = "rtasr.xfyun.cn/v1/ws";

    private static final String BASE_URL = "ws://" + HOST;

    private static final String ORIGIN = "http://" + HOST;
    private String mResPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_websocket;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(self);
        copyFile();
        mBtnConnect.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
        mTvShowMsg.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_connect:
                connect();
                break;
            case R.id.btn_send:
                if (mWebSocket != null) {
                    mWebSocket.send(mEtInputClient.getText().toString().trim());
                }
                break;
            case R.id.btn_close:
                if (mWebSocket != null) {
                    mWebSocket.close(1000, "任务完成");
                }
                break;
            default:
                break;
        }
    }

    private void connect() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
//        Request request = new Request.Builder().url(BASE_URL + getHandShakeParams(APPID, SECRET_KEY)).build();
        EchoWebSocketListener socketListener = new EchoWebSocketListener();
        mOkHttpClient.newWebSocket(request, socketListener);
        mOkHttpClient.dispatcher().executorService().shutdown();
    }

    final class EchoWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            Log.e("flag--", "onOpen(WebSocketActivity.java:82)---->>" + Thread.currentThread().getName());
            output("连接成功");
            mWebSocket = webSocket;
            sendBytes();
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
            output("断开连接中：" + reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            Log.e("flag--", "onClosed(WebSocketActivity.java:106)---->>" + Thread.currentThread().getName());
            output("断开连接完成：" + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            Log.e("flag--", "onFailure(WebSocketActivity.java:112)---->>" + Thread.currentThread().getName());
            output("连接失败");
        }
    }

    private void output(final String text) {
        Log.e("flag--", "WebSocketActivity.output(WebSocketActivity.java:111)--" + text);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvShowMsg.setText(mTvShowMsg.getText() + "\r\n" + text);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebSocket != null) {
            mWebSocket.cancel();
        }
    }

    private void copyFile() {
        mResPath = getExternalCacheDir().getAbsolutePath() + "/test01.pcm";
        Log.e("flag--", "copyFile(WebSocketActivity.java:150)---->>" + mResPath);
        if (!new File(mResPath).exists()) {
            try {
                FileOutputStream out = new FileOutputStream(mResPath);
                InputStream in = getAssets().open("test_1.pcm");
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1)
                    out.write(buffer, 0, readBytes);
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendBytes() {
        // 发送音频
        byte[] bytes = new byte[CHUNCKED_SIZE];
        try (RandomAccessFile raf = new RandomAccessFile(mResPath, "r")) {
            int len = -1;
            long lastTs = 0;
            while ((len = raf.read(bytes)) != -1) {
                if (len < CHUNCKED_SIZE) {
                    mWebSocket.send(ByteString.of(bytes, 0, len));
                    break;
                }

                long curTs = System.currentTimeMillis();
                if (lastTs == 0) {
                    lastTs = System.currentTimeMillis();
                } else {
                    long s = curTs - lastTs;
                    if (s < 40) {
                        System.out.println("error time interval: " + s + " ms");
                    }
                }
                mWebSocket.send(ByteString.of(bytes, 0, len));
                // 每隔40毫秒发送一次数据
                Thread.sleep(40);
            }

            // 发送结束标识
            mWebSocket.send("{\"end\": true}");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 生成握手参数
    public static String getHandShakeParams(String appId, String secretKey) {
        String ts = System.currentTimeMillis()/1000 + "";
        String signa = "";
        try {
            signa = EncryptUtil.HmacSHA1Encrypt(EncryptUtil.MD5(appId + ts), secretKey);
            return "?appid=" + appId + "&ts=" + ts + "&signa=" + URLEncoder.encode(signa, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}