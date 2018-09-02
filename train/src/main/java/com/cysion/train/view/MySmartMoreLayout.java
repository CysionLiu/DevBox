package com.cysion.train.view;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MySmartMoreLayout extends SmartRefreshLayout {
    public MySmartMoreLayout(Context context) {
        super(context);
        setEnableRefresh(false);
    }

    public MySmartMoreLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEnableRefresh(false);

    }

    public MySmartMoreLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setEnableRefresh(false);
    }
}
