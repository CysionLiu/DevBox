package com.cysion.baselib.simplify;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

/**
 * Activity生命周期监听器
 */
public class AppLifeCallBacker implements Application.ActivityLifecycleCallbacks {
    private int pageVisibleCount = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity != null) {
            String actyName = activity.getClass().getName();
            Logger.d("onActivityCreated: " + actyName);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity != null) {
            String actyName = activity.getClass().getName();
            Logger.d("onActivityStarted: " + actyName);
        }
        pageVisibleCount = pageVisibleCount + 1;
        if (pageVisibleCount == 1) {
            MyObserver.obj().onAppStatusChanged(true);
        }

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity != null) {
            String actyName = activity.getClass().getName();
            Logger.d("onActivityStopped: " + actyName);
        }
        pageVisibleCount = pageVisibleCount - 1;
        if (pageVisibleCount == 0) {
            MyObserver.obj().onAppStatusChanged(false);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
