package com.cysion.train.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cysion.baselib.Box;
import com.cysion.baselib.listener.Action;
import com.cysion.train.R;

public class ShareUtil {

    public static final String SHARE_WEIXIN = "share_weixin";
    public static final String SHARE_ERWEIMA = "erweima";
    public static final String CLOSE = "close";

    private static volatile ShareUtil instance;

    private ShareUtil() {

    }

    public static synchronized ShareUtil obj() {
        if (instance == null) {
            instance = new ShareUtil();
        }
        return instance;
    }

    public void popShareWindow(final Activity src, String t, final Action<String> aPureListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(src);
        LayoutInflater inflater = LayoutInflater.from(src);
        final AlertDialog dialog = builder.create();
        View view = inflater.inflate(R.layout.dialog_share, null);

        view.findViewById(R.id.rl_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aPureListener.done(SHARE_WEIXIN);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.rl_xquode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aPureListener.done(SHARE_ERWEIMA);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                aPureListener.done(CLOSE);
                dialog.dismiss();

            }
        });

        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        //摆脱token的限制，注意清单文件alert权限
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        window.getDecorView().setBackgroundColor(0X00000000);
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = (int) (Box.density()*145);
        p.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawable(null);
        window.setAttributes(p);
        window.setContentView(view);//自定义布局应该在这里添加，要在dialog.show()的后面
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }
}
