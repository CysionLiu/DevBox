package com.cysion.videosample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自动横向时间线，用于音频或者视频录制时的效果
 */
public class TimeAxiel extends View implements AnimState {

    /*
    时间轴的状态
     */
    private static final int IDLE = 110000;
    private static final int PLAYING = 110001;
    private static final int PAUSING = 110002;
    private static final int STOPPED = 110003;

    //每秒的刻度数目，一个周期即1s,大刻度正好偏移一次
    private static final int SCALECOUNT_PER_SECOND = 5;
    private static final int ONGOING_COLOR = Color.GRAY;
    private static final int PAUSING_COLOR = Color.GREEN;

    private int visibleScaleCount = 50;//可见的刻度数目，默认50个，10s，6个大刻度
    private int totalScaleCount = visibleScaleCount + SCALECOUNT_PER_SECOND;//全部的刻度数目，默认多一秒的刻度
    private float scaleHeight = 5;//普通刻度高度，大刻度1.5倍，单位dp
    private int frenquency = 40;//默认刷新频率，40ms算是一个低值
    private int textHeight = 10;//默认字的大小
    private int passedSecond = 0;//开启后的秒数，等于偏移小周期数目
    private int count = 0;//一个周期内的已刷新次数
    private int state = IDLE;
    private Paint mPaint;//画笔
    private float density;//屏幕密度


    public TimeAxiel(Context context) {
        super(context);
        init(context);
    }

    public TimeAxiel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeAxiel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        density = getResources().getDisplayMetrics().density;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        scaleHeight = scaleHeight * density;
        textHeight = (int) (textHeight * density);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (state == PAUSING) {
            mPaint.setColor(PAUSING_COLOR);
        } else {
            mPaint.setColor(ONGOING_COLOR);
        }
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //每个刻度的间距,由可见的刻度范围计算
        float widthPerScale = width / visibleScaleCount;
        //两个刻度再次重合时，刷新的次数
        int refreshCount = (1000 / SCALECOUNT_PER_SECOND) / frenquency;
        //计算每次刷新时，刻度偏移的距离
        float offsetPerFrame = widthPerScale / refreshCount;
        //当偏移超过一个大刻度时，即1s时，一个小周期重置
        if (count >= refreshCount * SCALECOUNT_PER_SECOND) {
            count = 0;
            //不是每次偏移刷新，立即改变时间文本，而是移动一个小周期时
            passedSecond = passedSecond + 1;
        }
        //遍历，画刻度
        for (int i = 0; i < totalScaleCount; i++) {
            if (i % SCALECOUNT_PER_SECOND == 0) {
                canvas.drawLine((widthPerScale * i - offsetPerFrame * count), (float) (height - 1.5 * scaleHeight),
                        widthPerScale * i - offsetPerFrame * count, height, mPaint);
            } else {
                canvas.drawLine((widthPerScale * i - offsetPerFrame * count), height - scaleHeight,
                        widthPerScale * i - offsetPerFrame * count, height, mPaint);
            }
        }
        int c = totalScaleCount / SCALECOUNT_PER_SECOND;
        for (int i = 0; i < c; i++) {
            int k = i * SCALECOUNT_PER_SECOND;
            canvas.drawText(getTimeText(i), widthPerScale * k - offsetPerFrame * count - textHeight / 2,
                    height - scaleHeight - 10 * density
                    , mPaint);
        }

        canvas.drawLine(0, height, width, height, mPaint);
        count = count + 1;
        if (state == PLAYING) {
            postInvalidateDelayed(40);
        }
    }

    private String getTimeText(int i) {
        String time = secToTime(i + passedSecond);
        return time;
    }


    public void setVisibleScaleCount(int aVisibleScaleCount) {
        visibleScaleCount = aVisibleScaleCount;
        totalScaleCount = visibleScaleCount + SCALECOUNT_PER_SECOND;
    }

    public void setScaleHeight(int aScaleHeight) {
        scaleHeight = aScaleHeight;
        scaleHeight = scaleHeight * density;
    }

    public void setFrenquency(int aFrenquency) {
        frenquency = aFrenquency;
    }

    public void setTextHeight(int aTextHeight) {
        textHeight = aTextHeight;
        textHeight = (int) (textHeight * density);
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
        if (state == PLAYING) {
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
        passedSecond = 0;
        count = 0;
    }

    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else retStr = "" + i;
        return retStr;
    }
}
