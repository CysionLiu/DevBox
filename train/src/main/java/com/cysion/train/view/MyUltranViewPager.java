package com.cysion.train.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.cysion.baselib.Box;
import com.tmall.ultraviewpager.UltraViewPager;

public class MyUltranViewPager extends UltraViewPager {
    public MyUltranViewPager(Context context) {
        super(context);
        setup(context);
    }


    public MyUltranViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public MyUltranViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }


    private void setup(Context aContext) {
        setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //内置indicator初始化
        initIndicator();
        //设置indicator样式
        getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.YELLOW)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
        //设置indicator对齐方式
        getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //构造indicator,绑定到UltraViewPager
        getIndicator().setMargin(0, 0, 0, (int) (6 * Box.density())).build();

        //设定页面循环播放
        setInfiniteLoop(true);
        //设定页面自动切换  间隔2秒
        setAutoScroll(3000);
    }
}
