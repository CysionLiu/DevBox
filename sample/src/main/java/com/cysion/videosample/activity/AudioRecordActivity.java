package com.cysion.videosample.activity;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.videosample.R;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import omrecorder.AudioChunk;
import omrecorder.AudioRecordConfig;
import omrecorder.OmRecorder;
import omrecorder.PullTransport;
import omrecorder.PullableSource;
import omrecorder.Recorder;

/**
 * 音频录制，基于audiorecord，过程中音频是pcm格式
 */
public class AudioRecordActivity extends BaseActivity {

    public static final int FREQUENCY = 16000;
    public static final int IDLE = 340;
    public static final int PLAYING = 341;
    public static final int PAUSING = 342;
    public static final int FINISHED = 343;
    Recorder recorder;
    ImageView recordButton;
    private String mPath;
    private int mRecordState = IDLE;
    private ImageView mIvPlay;
    private ImageView mIvStop;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recorder;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(self);
        mPath = getExternalCacheDir().getAbsolutePath() + "/pcmtest.wav";
        setupRecorder();
        mIvPlay = findViewById(R.id.iv_play_or_pause);
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
                    Toast.makeText(AudioRecordActivity.this, "暂停录音", Toast.LENGTH_SHORT).show();

                } else if (mRecordState == PAUSING) {
                    recorder.resumeRecording();
                    mIvPlay.setImageResource(R.drawable.pause);
                    Toast.makeText(AudioRecordActivity.this, "继续录音", Toast.LENGTH_SHORT).show();
                    mRecordState = PLAYING;

                } else {
                    mIvPlay.setImageResource(R.drawable.pause);
                    recorder.startRecording();
                    mRecordState = PLAYING;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sendBytes();
                        }
                    }).start();
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
                    @Override
                    public void onAudioChunkPulled(AudioChunk audioChunk) {
                        animateVoice((float) (audioChunk.maxAmplitude() / 200.0));
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
        return new File(mPath);
    }

    private static final int CHUNCKED_SIZE = 1280;

    private void sendBytes() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException aE) {
            aE.printStackTrace();
        }
        // 发送音频
        byte[] bytes = new byte[CHUNCKED_SIZE];
        try (RandomAccessFile raf = new RandomAccessFile(mPath, "r")) {
            int len = -1;
            long lastTs = 0;
            while ((len = raf.read(bytes)) != -1 || (mRecordState == PLAYING || mRecordState == PAUSING)) {
                if (len < CHUNCKED_SIZE) {
                    if ((mRecordState == PLAYING || mRecordState == PAUSING)) {
                        Log.i("flag--", "WavRecorderActivity.sendBytes(WavRecorderActivity.java:201)--" + len);
                        continue;
                    }
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
                Log.i("flag--", "PcmRecorderActivity.sendBytes(PcmRecorderActivity.java:199)--" + len);
                // 每隔40毫秒发送一次数据
                Thread.sleep(40);
            }
            Log.i("flag--", "PcmRecorderActivity.sendBytes(PcmRecorderActivity.java:205)--");

            // 发送结束标识
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRecord();
    }

}
