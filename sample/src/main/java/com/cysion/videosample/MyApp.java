package com.cysion.videosample;

import android.app.Application;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.Utils;
import com.cysion.baselib.Box;
import com.cysion.baselib.simplify.AppLifeCallBacker;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MyApp extends Application {
    public static final boolean debug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        Box.init(this, debug);
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return debug;
            }
        });
        Utils.init(this);
        registerActivityLifecycleCallbacks(new AppLifeCallBacker());

    }
}
