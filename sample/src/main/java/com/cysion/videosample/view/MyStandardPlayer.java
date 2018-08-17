package com.cysion.videosample.view;

import android.content.Context;
import android.util.AttributeSet;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class MyStandardPlayer extends StandardGSYVideoPlayer {
    public MyStandardPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MyStandardPlayer(Context context) {
        super(context);
    }

    public MyStandardPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
