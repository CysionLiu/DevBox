package com.cysion.train.simple;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.common.ImageLoader;

public class PicassoImageLoader implements ImageLoader {


    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path)
                .into(imageView);
    }
}
