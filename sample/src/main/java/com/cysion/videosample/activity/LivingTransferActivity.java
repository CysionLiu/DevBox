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
import com.cysion.baselib.listener.TypeAction;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.videosample.R;
import com.cysion.videosample.util.AudioSocketClient;

import java.io.File;
import java.io.IOException;

import okio.ByteString;
import omrecorder.AudioChunk;
import omrecorder.AudioRecordConfig;
import omrecorder.OmRecorder;
import omrecorder.PullTransport;
import omrecorder.PullableSource;
import omrecorder.Recorder;

public class LivingTransferActivity extends BaseActivity implements TypeAction<String> {

    public static final int FREQUENCY = 16000;
    public static final int IDLE = 340;
    public static final int PLAYING = 341;
    public static final int PAUSING = 342;
    public static final int FINISHED = 343;
    Recorder recorder;
    ImageView recordButton;
    private String mResPath;
    private int mRecordState = IDLE;
    private ImageView mIvPlay;
    private ImageView mIvStop;
    private TextView mTvTarget;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recorder;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(self);
        mResPath = getExternalCacheDir().getAbsolutePath() + "/pcmtest.wav";
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
                    AudioSocketClient.obj().keepLive();
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
                    if (mRecordState == FINISHED) {
                        setupRecorder();
                    }
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
        recorder = OmRecorder.wav(
                new PullTransport.Default(mic(), new PullTransport.OnAudioChunkPulledListener() {
                    private float lastPeek = 0.0f;

                    @Override
                    public void onAudioChunkPulled(AudioChunk audioChunk) {
                        float peek = (float) (audioChunk.maxAmplitude() / 200.0);
                        animateVoice(peek);
                        lastPeek = peek;
                        if (peek >= 0.3f || (peek < 0.3f && lastPeek >= 0.25f)) {
                            sendLivingStream(audioChunk.toBytes());
                        } else {
                            AudioSocketClient.obj().keepLive();
                        }
                    }
                }), file());
    }


    private void animateVoice(final float maxPeak) {
        Log.e("flag--", "animateVoice(LivingTransferActivity.java:136)---->>" + maxPeak);
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
        AudioSocketClient.obj().connect(this, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRecord();
        AudioSocketClient.obj().cancel();
    }

    private void sendLivingStream(byte[] aBytes) {
        AudioSocketClient.obj().sendBytes(ByteString.of(aBytes));
    }

    @Override
    public void done(final String aS, final int type) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (type == AudioSocketClient.CONNECTED) {
                    mTvTarget.setText(aS);
                }
            }
        });

    }
}
