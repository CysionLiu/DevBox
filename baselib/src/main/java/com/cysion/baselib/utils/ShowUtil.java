package com.cysion.baselib.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

public class ShowUtil {
    public static void darkAndWhite(Activity aActivity, boolean isBlack) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setColor(aActivity, Color.WHITE, 0);
            if (isBlack) {
                aActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                aActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }

        }
    }

    //普通主题状态栏，文字是白色；color-状态栏背景颜色
    public static void normalColor(Activity aActivity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setColor(aActivity, color, 0);
            aActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
        }
    }

    //黑色主题状态栏，文字是黑色；color-状态栏背景颜色
    public static void darkColor(Activity aActivity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setColor(aActivity, color, 0);
            aActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//恢复状态栏白色字体
        }
    }
}
