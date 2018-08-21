package com.cysion.train.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cysion.baselib.Box;
import com.cysion.train.R;

public class MyToast {
    private Toast mToast;
    int bgColor = -1;
    int tvColor = -1;
    String text = "";
    int iconId = -1;
    int gravity = -1;
    int yOffset = -1;
    int duration = -1;


    public static class Builder {
        int bgColor = -1;
        int tvColor = -1;
        String text = "";
        int iconId = -1;
        int gravity = -1;
        int yOffset = -1;
        int duration = -1;

        public Builder bgColor(int aBgColor) {
            bgColor = aBgColor;
            return this;
        }

        public Builder tvColor(int aTvColor) {
            tvColor = aTvColor;
            return this;
        }

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

        public Builder text(String aText) {
            text = aText;
            return this;
        }

        public MyToast build() {
            return new MyToast(this);
        }
    }


    @SuppressLint("WrongConstant")
    private MyToast(Builder aBuilder) {
        text = aBuilder.text;
        if (aBuilder.bgColor >= 0) {
            bgColor = aBuilder.bgColor;
        }
        if (aBuilder.tvColor >= 0) {
            tvColor = aBuilder.tvColor;
        }
        if (aBuilder.iconId > 0) {
            iconId = aBuilder.iconId;
        }
        gravity = aBuilder.gravity;
        yOffset = aBuilder.yOffset;
        if (aBuilder.duration < 0) {
            duration = Toast.LENGTH_SHORT;
        } else {
            duration = Toast.LENGTH_LONG;
        }
        duration = aBuilder.duration;
        if (iconId < 0) {
            View v = LayoutInflater.from(Box.ctx()).inflate(R.layout.toast_text, null);
            TextView textView = v.findViewById(R.id.tv_show);
            textView.setText(text);
            if (bgColor >= 0) {
                v.setBackgroundColor(bgColor);
            }
            if (tvColor >= 0) {
                textView.setTextColor(tvColor);
            }
            mToast = new Toast(Box.ctx());
            mToast.setGravity(gravity, 0, yOffset);
            mToast.setDuration(duration);
            mToast.setView(v);
        } else {
            View v = LayoutInflater.from(Box.ctx()).inflate(R.layout.toast_hyper_text, null);
            TextView textView = v.findViewById(R.id.tv_show);
            ImageView imageView = v.findViewById(R.id.iv_show);
            imageView.setImageResource(iconId);
            textView.setText(text);
            if (bgColor >= 0) {
                v.setBackgroundColor(bgColor);
            }
            if (tvColor >= 0) {
                textView.setTextColor(tvColor);
            }
            mToast = new Toast(Box.ctx());
            mToast.setGravity(gravity, 0, yOffset);
            mToast.setDuration(duration);
            mToast.setView(v);
        }
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }
}