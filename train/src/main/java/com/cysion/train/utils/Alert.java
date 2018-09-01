package com.cysion.train.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cysion.baselib.Box;
import com.cysion.train.R;

/**
 * Created by cysion on 2018\2\11 0011.
 */

public class Alert {

    private static volatile Alert instance;
    private AlertDialog mLoadDialog;

    private Alert() {

    }

    public static synchronized Alert obj() {
        if (instance == null) {
            instance = new Alert();
        }
        return instance;
    }


    public void loading(final Activity src) {
        AlertDialog.Builder builder = new AlertDialog.Builder(src);
        LayoutInflater inflater = LayoutInflater.from(src);
        View view = inflater.inflate(R.layout.dialog_loading, null);
        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        //摆脱token的限制，注意清单文件alert权限
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        window.getDecorView().setBackgroundColor(0X00000000);
        p.width = (int) (Box.w() * 1f);
        window.setAttributes(p);
        window.setDimAmount(0);
        window.setBackgroundDrawable(null);
        dialog.getWindow().setContentView(view);//自定义布局应该在这里添加，要在dialog.show()的后面
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.setCancelable(true);
        mLoadDialog = dialog;
    }

    public void loaded() {
        if (mLoadDialog != null) {
            mLoadDialog.dismiss();
        }
    }

    public void showUserService(final Activity src, String t) {
        AlertDialog.Builder builder = new AlertDialog.Builder(src);
        LayoutInflater inflater = LayoutInflater.from(src);
        View view = inflater.inflate(R.layout.dialog_user_service, null);
        TextView text = view.findViewById(R.id.tv_desc);
        text.setMovementMethod(new ScrollingMovementMethod());
        text.setText(Html.fromHtml(t));
        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        //摆脱token的限制，注意清单文件alert权限
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        window.getDecorView().setBackgroundColor(0X00000000);
        window.setBackgroundDrawable(null);
        p.width = (int) (Box.w() * 0.85f);
        window.setAttributes(p);
        dialog.getWindow().setContentView(view);//自定义布局应该在这里添加，要在dialog.show()的后面
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }
}
