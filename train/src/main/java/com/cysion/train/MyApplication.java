package com.cysion.train;

import android.app.Application;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.Utils;
import com.cysion.baselib.Box;
import com.cysion.baselib.net.AInjector;
import com.cysion.baselib.net.Caller;
import com.cysion.baselib.simplify.AppLifeCallBacker;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {

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
        //初始化网络请求
        Caller.obj().inject(Constant.HOST, new AInjector() {
            @Override
            public Map<String, String> headers() {
                return new HashMap<>();
            }
        });

    }
}
