package com.cysion.train.view;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MySmartRefreshLayout extends SmartRefreshLayout {
    public MySmartRefreshLayout(Context context) {
        super(context);
        setEnableLoadMore(false);
    }

    public MySmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEnableLoadMore(false);
    }

    public MySmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setEnableLoadMore(false);
    }
}
