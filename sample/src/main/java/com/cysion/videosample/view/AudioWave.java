package com.cysion.videosample.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AudioWave extends View implements AnimState {
    /*
   时间轴的状态
    */
    private static final int IDLE = 110000;
    private static final int PLAYING = 110001;
    private static final int PAUSING = 110002;
    private static final int STOPPED = 110003;

    public AudioWave(Context context) {
        super(context);
    }

    public AudioWave(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AudioWave(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onResumed() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void release() {

    }
}
