package com.cysion.train.view;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cysion.baselib.Box;
import com.cysion.train.R;

public class MyToast {
    private Toast mToast;
    String text = "";
    int iconId = -1;
    int gravity = -1;
    int yOffset = -1;
    int duration = -1;
    int textDp = 14;


    public static class Builder {
        String text = "";
        int iconId = -1;
        int gravity = -1;
        int yOffset = -1;
        int duration = -1;
        int textDp = 14;

        public Builder iconId(int aIconId) {
            iconId = aIconId;
            return this;
        }

        public Builder gravity(int aGravity) {
            gravity = aGravity;
            return this;
        }

        public Builder yOffset(int aYOffset) {
            yOffset = aYOffset;
            return this;
        }

        public Builder duration(int aDuration) {
            duration = aDuration;
            return this;
        }

        public Builder textSize(int aSize) {
            textDp = aSize;
            return this;
        }

        public Builder text(String aText) {
            text = aText;
            return this;
        }

        public MyToast buildToShow() {
            return new MyToast(this);
        }


    }


    @SuppressLint("WrongConstant")
    private MyToast(Builder aBuilder) {
        text = aBuilder.text;
        if (aBuilder.iconId > 0) {
            iconId = aBuilder.iconId;
        }
        gravity = aBuilder.gravity;
        if (gravity < 0) {
            gravity = Gravity.CENTER;
        }
        yOffset = aBuilder.yOffset;
        if (aBuilder.duration < 0) {
            duration = Toast.LENGTH_SHORT;
        } else {
            duration = Toast.LENGTH_LONG;
        }
        duration = aBuilder.duration;
        textDp = aBuilder.textDp;
        if (TextUtils.isEmpty(text)) {
            ImageView img = new ImageView(Box.ctx());
            img.setImageResource(R.drawable.login_success);
            mToast = new Toast(Box.ctx());
            mToast.setGravity(gravity, 0, yOffset);
            mToast.setDuration(duration);
            mToast.setView(img);
            mToast.show();
            return;
        }
        if (iconId < 0) {
            View v = LayoutInflater.from(Box.ctx()).inflate(R.layout.toast_text, null);
            TextView textView = v.findViewById(R.id.tv_show);
            textView.setTextSize(textDp);
            textView.setText(text);
            mToast = new Toast(Box.ctx());
            mToast.setGravity(gravity, 0, yOffset);
            mToast.setDuration(duration);
            mToast.setView(v);
            mToast.show();
        } else {
            View v = LayoutInflater.from(Box.ctx()).inflate(R.layout.toast_hyper_text, null);
            TextView textView = v.findViewById(R.id.tv_show);
            ImageView imageView = v.findViewById(R.id.iv_show);
            imageView.setImageResource(iconId);
            textView.setTextSize(textDp);
            textView.setText(text);
            mToast = new Toast(Box.ctx());
            mToast.setGravity(gravity, 0, yOffset);
            mToast.setDuration(duration);
            mToast.setView(v);
            mToast.show();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static void quickShow(String msg) {
        builder().text(msg).buildToShow();
    }
}