package com.cysion.train.utils;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

public class URLImageParser implements Html.ImageGetter {
    TextView mTextView;


    public URLImageParser(TextView textView) {
        this.mTextView = textView;
    }

    @Override
    public Drawable getDrawable(final String source) {
        Drawable urlDrawable = null;
        try {
            urlDrawable = Glide.with(mTextView.getContext()).load(source).into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
            mTextView.invalidate();
            mTextView.setText(mTextView.getText());
        } catch (InterruptedException aE) {
            aE.printStackTrace();
        } catch (ExecutionException aE) {
            aE.printStackTrace();
        }
        return urlDrawable;
    }
}