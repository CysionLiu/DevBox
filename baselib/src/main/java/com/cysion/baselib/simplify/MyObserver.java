package com.cysion.baselib.simplify;

import android.util.Log;

import com.cysion.baselib.listener.AppStatusListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cysion on 2018\8\2 0002.
 * 单例类，持有一些状态观察者的集合
 */

public class MyObserver {
    private static volatile MyObserver instance;
    private List<AppStatusListener> mAppStatusListeners;

    private MyObserver() {
        mAppStatusListeners = new ArrayList<>();
    }

    public static synchronized MyObserver obj() {
        if (instance == null) {
            instance = new MyObserver();
        }
        return instance;
    }

    //app切换到前台后台
    public void onAppStatusChanged(boolean isFront) {
        Log.v("flag--", "MyObserver.onAppStatusChanged(MyObserver.java:32)--is front:" + isFront);
        for (AppStatusListener appStatusListener : mAppStatusListeners) {
            appStatusListener.isFront(isFront);
        }
    }

    public void addAppStatusListener(AppStatusListener aAppStatusListener) {
        mAppStatusListeners.add(aAppStatusListener);
    }

    public void delAppStatusListener(AppStatusListener aAppStatusListener) {
        if (aAppStatusListener != null) {
            mAppStatusListeners.remove(aAppStatusListener);
        }
    }

    public void clear() {
        mAppStatusListeners.clear();
    }

}
