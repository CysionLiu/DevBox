package com.cysion.baselib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by cysion on 2017\12\22 0022.
 * 主要放置相关的配置信息，简短使用
 */

public class Box {

    private static Context ctx;
    private static boolean isDebug;
    private static int width;
    private static int height;
    private static Configuration cfg;
    private static Resources res;
    private static String uuid = "";

    //Application创建时就需要调用此方法
    public static void init(Context aContext, boolean aIsDebug) {
        ctx = aContext.getApplicationContext();
        isDebug = aIsDebug;
        initSize();
        res = ctx.getResources();
        cfg = res.getConfiguration();
    }

    private static void initSize() {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public static Context ctx() {
        return ctx;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static int w() {
        return width;
    }

    public static int h() {
        return height;
    }

    public static Configuration cfg() {
        return cfg;
    }

    public static Resources res() {
        return res;
    }

    public static Drawable drawable(int did) {
        return res().getDrawable(did);
    }

    //string
    public static String str(int sid) {
        return res.getString(sid);
    }

    //color
    public static int color(int sid) {
        return res.getColor(sid);
    }

    //drawable
    public static Drawable img(int sid) {
        return res.getDrawable(sid);
    }

    //drawable
    public static float density() {
        return res().getDisplayMetrics().density;
    }


    //version code
    public static int vcode() {
        if (ctx == null) {
            return 999999;
        }
        PackageManager packManager = ctx.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packManager.getPackageInfo(ctx.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 999999;
    }


    //version name
    public static String vName() {
        if (ctx == null) {
            return "999999";
        }
        PackageManager packManager = ctx.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packManager.getPackageInfo(ctx.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException aE) {
            aE.printStackTrace();
        }
        return packageInfo.versionName;
    }

    public static void toast(String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(int rid) {
        Toast.makeText(ctx, str(rid), Toast.LENGTH_SHORT).show();
    }

    public static PackageInfo pInfo() {
        PackageManager packManager = ctx.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packManager.getPackageInfo(ctx.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException aE) {
            aE.printStackTrace();
        }
        return packageInfo;
    }

    public static String uuid() {
        if (!TextUtils.isEmpty(uuid)) {
            return uuid;
        }
        //兼容老用户
        TelephonyManager mTelephonyMgr = (TelephonyManager) ctx
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyMgr != null) {
            uuid = mTelephonyMgr.getDeviceId();
        }
        if (!TextUtils.isEmpty(uuid) && !uuid.startsWith("0")) {
            return uuid;
        }
        //没有获得imme，则获取硬件序列号
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            uuid = Build.getSerial();
        } else {
            uuid = Build.SERIAL;
        }
        return uuid;
    }
}
