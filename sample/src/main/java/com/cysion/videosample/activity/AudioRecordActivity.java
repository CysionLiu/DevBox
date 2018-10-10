package com.cysion.videosample.activity;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

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
import omrecorder.WriteAction;

/**
 * 音频录制，基于audiorecord，过程中音频是pcm格式
 */
public class AudioRecordActivity extends BaseActivity {

    Recorder recorder;
    ImageView recordButton;
    CheckBox skipSilence;
    private Button pauseResumeButton;
    private String mPath;
    boolean isRunning;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recorder;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(self);
        mPath = getExternalCacheDir().getAbsolutePath() + "/pcmtest.wav";
        setupRecorder();
        skipSilence = (CheckBox) findViewById(R.id.skipSilence);
        skipSilence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setupNoiseRecorder();
                } else {
                    setupRecorder();
                }
            }
        });
        recordButton = (ImageView) findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recorder.startRecording();
                isRunning = true;
                skipSilence.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendBytes();
                    }
                }).start();
            }
        });
        findViewById(R.id.stopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recorder.stopRecording();
                    isRunning = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                skipSilence.setEnabled(true);
                recordButton.post(new Runnable() {
                    @Override
                    public void run() {
                        animateVoice(0);
                    }
                });
            }
        });
        pauseResumeButton = (Button) findViewById(R.id.pauseResumeButton);
        pauseResumeButton.setOnClickListener(new View.OnClickListener() {
            boolean isPaused = false;

            @Override
            public void onClick(View view) {
                if (recorder == null) {

                    return;
                }
                if (!isPaused) {
                    pauseResumeButton.setText(getString(R.string.resume_recording));
                    recorder.pauseRecording();
                    pauseResumeButton.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animateVoice(0);
                        }
                    }, 100);
                } else {
                    pauseResumeButton.setText(getString(R.string.pause_recording));
                    recorder.resumeRecording();
                }
                isPaused = !isPaused;
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

    private void setupNoiseRecorder() {
        recorder = OmRecorder.wav(
                new PullTransport.Noise(mic(),
                        new PullTransport.OnAudioChunkPulledListener() {
                            @Override
                            public void onAudioChunkPulled(AudioChunk audioChunk) {
                                animateVoice((float) (audioChunk.maxAmplitude() / 200.0));
                            }
                        },
                        new WriteAction.Default(),
                        new Recorder.OnSilenceListener() {
                            @Override
                            public void onSilence(long silenceTime) {

                            }
                        }, 200
                ), file()
        );
    }

    private void animateVoice(final float maxPeak) {
        recordButton.animate().scaleX(1 + maxPeak).scaleY(1 + maxPeak).setDuration(10).start();
    }

    private PullableSource mic() {
        return new PullableSource.Default(
                new AudioRecordConfig.Default(
                        MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT,
                        AudioFormat.CHANNEL_IN_MONO, 16000
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
            while ((len = raf.read(bytes)) != -1 || isRunning) {
                if (len < CHUNCKED_SIZE) {
                    if (isRunning) {
                        Log.i("flag--","WavRecorderActivity.sendBytes(WavRecorderActivity.java:201)--"+len);
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
        if (recorder != null && isRunning) {
            try {
                recorder.stopRecording();
                isRunning = false;
            } catch (IOException aE) {
                aE.printStackTrace();
            }
        }
    }

}
