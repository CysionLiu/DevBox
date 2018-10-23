package com.cysion.videosample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class AudioWave extends View implements AnimState {
    /*
   时间轴的状态
    */
    private static final int IDLE = 110000;
    private static final int PLAYING = 110001;
    private static final int PAUSING = 110002;
    private static final int STOPPED = 110003;

    private static final int ONGOING_COLOR = Color.RED;
    private static final int PAUSING_COLOR = Color.GRAY;

    private int dotCount = 120;//屏幕上点的数量
    private float newDotPeek = 0.0f;//新加入点的振幅高度，在本view中，等于0.5声音振幅
    private int frenquency = 40;//默认刷新频率，40ms算是一个低值
    private float[] dotPeeks = new float[dotCount];
    private int state = IDLE;
    private Paint mPaint;//画笔
    private Path mPath;
    private float density;//屏幕密度

    public AudioWave(Context context) {
        super(context);
        init(context);
    }

    public AudioWave(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AudioWave(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        density = getResources().getDisplayMetrics().density;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1 * density);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (state == PAUSING) {
            mPaint.setColor(PAUSING_COLOR);
        } else {
            mPaint.setColor(ONGOING_COLOR);
        }
        mPath.reset();
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float dotSpace = width*1.0f / dotCount;
        Log.e("flag--","onDraw(AudioWave.java:72)---->>"+width);
        Log.e("flag--","onDraw(AudioWave.java:72)---->>"+dotSpace);
        for (int i = 0; i < dotCount; i++) {
            if (dotCount - 1 == i) {
                dotPeeks[0] = newDotPeek;
            } else {
                dotPeeks[dotCount - i - 1] = dotPeeks[dotCount - i - 2];
            }
        }
        for (int i = 1; i < dotCount-3; i++) {
            canvas.drawLine(dotSpace * i, (0.5f + dotPeeks[i] / 2) * height,
                    dotSpace * i, (0.5f - dotPeeks[i] / 2) * height, mPaint);
        }
        mPaint.setStrokeWidth(3);
        if (state == PAUSING) {
            mPaint.setColor(Color.GREEN);
        } else {
            mPaint.setColor(ONGOING_COLOR);
        }
        float r = 5*density;
        canvas.drawLine(width-r, 0, width-r, height, mPaint);
        canvas.drawCircle(width - r, r,r, mPaint);
        canvas.drawCircle(width - r, height - r, r, mPaint);
        mPaint.setStrokeWidth(1 * density);
        setNewDotPeek(0);
        if (state == PLAYING) {
            postInvalidateDelayed(frenquency);
            getContext().getExternalCacheDir();
        }
    }

    @Override
    public void onStart() {
        if (state == PLAYING || state == PAUSING) {
            return;
        }
        release();
        invalidate();
        state = PLAYING;
    }

    @Override
    public void onPaused() {
        state = PAUSING;
    }

    @Override
    public void onResumed() {
        if (state == PLAYING || state == STOPPED) {
            return;
        }
        state = PLAYING;
        invalidate();
    }

    @Override
    public void onStopped() {
        state = STOPPED;
    }

    @Override
    public void release() {
        state = IDLE;
        dotPeeks = new float[dotCount];
        newDotPeek = 0.0f;
    }

    public void setNewDotPeek(float aNewDotPeek) {
//        newDotPeek = aNewDotPeek;
        newDotPeek = new Random().nextFloat() / 2;
    }
}
