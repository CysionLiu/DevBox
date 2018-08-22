package com.cysion.baselib.utils;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;

/**
 * Created by xianshang.liu on 2016/12/14.
 */

public class ViewUtil {

    private static volatile ViewUtil instance;

    private ViewUtil() {

    }

    public static synchronized ViewUtil obj() {
        if (instance == null) {
            instance = new ViewUtil();
        }
        return instance;
    }

    //优化内存
    public void gcViews(View view) {
        if (view == null) {
            return;
        }
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            if (view instanceof WebView) {
                ((WebView) view).removeAllViews();
                ((WebView) view).destroy();
                return;
            }
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                gcViews(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

}
