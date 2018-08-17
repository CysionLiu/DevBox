package com.cysion.baselib.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

public class ShowUtil {
    public static void darkAndWhite(Activity aActivity, boolean isBlack) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setTransparent(aActivity);
            if (isBlack) {
                aActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                aActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }
}
