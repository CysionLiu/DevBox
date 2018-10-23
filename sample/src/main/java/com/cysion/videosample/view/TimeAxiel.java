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
    private static final int PAUSING_COLOR = Color.GRAY;
    public static final int MILL_PER_BIG_SCALE = 1000;//每个大刻度时间，ms

    private int visibleScaleCount = 25;//可见的刻度数目，默认50个，10s，6个大刻度
    private int totalScaleCount = visibleScaleCount + SCALECOUNT_PER_SECOND;//全部的刻度数目，默认多一秒的刻度
    private int textCount = totalScaleCount / SCALECOUNT_PER_SECOND;//时间文本的数目
    private float scaleHeight = 5;//普通刻度高度，大刻度1.5倍，单位dp
    private int frenquency = 40;//默认刷新频率，40ms算是一个低值
    private int textHeight = 12;//默认字的大小
    private long passedSecond = 0;//开启后的毫秒数，等于偏移小周期数目
    private int count = 0;//一个周期内的已刷新次数
    private int state = IDLE;
    private Paint mPaint;//画笔
    private float density;//屏幕密度
    private boolean isAuto = true;//是否自动刷新，而不受外界控制
    private String[] timeTexts;//时间文本集合

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
        initTimeText();
        density = getResources().getDisplayMetrics().density;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        scaleHeight = scaleHeight * density;
        textHeight = (int) (textHeight * density);
        mPaint.setTextSize(textHeight);
    }

    private void initTimeText() {
        timeTexts = new String[textCount];
        for (int i = 0; i < textCount; i++) {
            if (i == (textCount - 1) / 2) {
                timeTexts[i] = milliSecToTime(passedSecond);
            } else {
                timeTexts[i] = milliSecToTime(passedSecond + (i - (textCount - 1) / 2) * MILL_PER_BIG_SCALE);
            }
        }
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
        float widthPerScale = width * 1.0f / visibleScaleCount;
        //两个刻度再次重合时，刷新的次数
        int refreshCount = (MILL_PER_BIG_SCALE / SCALECOUNT_PER_SECOND) / frenquency;
        //计算每次刷新时，刻度偏移的距离
        float offsetPerFrame = 1.0f * widthPerScale / refreshCount;
        //当偏移超过一个大刻度时，即1s时，一个小周期重置
        if (count >= refreshCount * SCALECOUNT_PER_SECOND) {
            count = 0;
            //不是每次偏移刷新，立即改变时间文本，而是移动一个小周期时
            if (isAuto) {
                passedSecond = passedSecond + MILL_PER_BIG_SCALE;
            }
            //时间文本集合内容前移一个，最后的补上最新时间
            for (int i = 0; i < textCount; i++) {
                if (textCount - 1 == i) {
                    //最后一个文本修改为最新时间+半范围
                    timeTexts[i] = milliSecToTime(passedSecond + MILL_PER_BIG_SCALE * textCount / 2);
                } else {
                    //之前的文本前移一个
                    timeTexts[i] = timeTexts[i + 1];
                }
            }
        }

        //遍历，画刻度
        for (int i = 0; i < totalScaleCount; i++) {
            if (i % SCALECOUNT_PER_SECOND == 0) {
                canvas.drawLine((widthPerScale * i - offsetPerFrame * (count - 2 * refreshCount)), (float) (height - 1.5 * scaleHeight),
                        widthPerScale * i - offsetPerFrame * (count - 2 * refreshCount), height, mPaint);
            } else {
                canvas.drawLine((widthPerScale * i - offsetPerFrame * (count - 2 * refreshCount)), height - scaleHeight,
                        widthPerScale * i - offsetPerFrame * (count - 2 * refreshCount), height, mPaint);
            }
        }
        int c = textCount;
        for (int i = 0; i < c; i++) {
            int k = i * SCALECOUNT_PER_SECOND;
            canvas.drawText(timeTexts[i], widthPerScale * k - offsetPerFrame * (count - 2 * refreshCount) - textHeight / 3,
                    height - scaleHeight - 10 * density
                    , mPaint);
        }

        canvas.drawLine(0, height, width, height, mPaint);
        count = count + 1;
        if (PLAYING == state && isAuto) {
            postInvalidateDelayed(frenquency);
        }
    }

    public void setPassedSecond(long aPassedSecond) {
        if (!isAuto) {
            passedSecond = aPassedSecond;
            invalidate();
        }
    }

    //锁屏后校正显示不一致
    public void adjust() {
        initTimeText();
    }

    @Override
    public void onStart() {
        if (state == PLAYING || state == PAUSING) {
            return;
        }
        release();
        state = PLAYING;
        if (isAuto) {
            invalidate();
        }
    }

    @Override
    public void onPaused() {
        if (state == PLAYING) {
            state = PAUSING;
        }
    }

    @Override
    public void onResumed() {
        if (state == PLAYING || state == STOPPED) {
            return;
        }
        state = PLAYING;
    }

    @Override
    public void onStopped() {
        state = STOPPED;
    }

    @Override
    public void release() {
        state = IDLE;
        passedSecond = 0;
        adjust();
        count = 0;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public static String milliSecToTime(long milliseconds) {
        String timeStr = null;
        int time = (int) (milliseconds / 1000);
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (milliseconds <= 0) {
            return "00:00";
        } else if (time <= 0)
            return "00:01";
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
