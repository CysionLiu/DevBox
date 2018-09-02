package com.cysion.train;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.Utils;
import com.cysion.baselib.Box;
import com.cysion.baselib.net.AInjector;
import com.cysion.baselib.net.Caller;
import com.cysion.baselib.simplify.AppLifeCallBacker;
import com.cysion.train.simple.UserCaller;
import com.cysion.train.view.MySmartRefreshLayout;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {

    public static final boolean debug = true;

    static {
        //设置全局的Header构建器
        MySmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.main_background, R.color.sub_text);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Header构建器
        MySmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {

            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.main_background, R.color.sub_text);//全局设置主题颜色
                return new ClassicsFooter(context);
            }
        });
    }

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
        initCaller();

    }

    private void initCaller() {
        Caller.obj().inject(Constant.HOST, new AInjector() {
            @Override
            public Map<String, String> headers() {
                Map<String, String> map = new HashMap<>();
                map.put("version", String.valueOf(AppUtils.getAppVersionCode()));
                map.put("type", "android");
                map.put("uuid", Box.uuid());
                map.put("model", DeviceUtils.getModel());
                return map;
            }
        });
        UserCaller.obj().inject(Constant.PASSPORT_HOST, null);
    }
}
