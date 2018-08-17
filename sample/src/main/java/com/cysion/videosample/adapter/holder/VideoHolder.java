package com.cysion.videosample.adapter.holder;

import android.view.View;

import com.bumptech.glide.Glide;
import com.cysion.videosample.R;
import com.cysion.videosample.VideoConstant;
import com.cysion.videosample.base.BaseViewHolder;
import com.cysion.videosample.entity.VideoEntity;
import com.cysion.videosample.view.MyStandardPlayer;
import com.orhanobut.logger.Logger;

public class VideoHolder extends BaseViewHolder<VideoEntity> {

    private final MyStandardPlayer mPlayer;

    public VideoHolder(View itemView) {
        super(itemView);
        mPlayer = itemView.findViewById(R.id.detail_player);
    }

    @Override
    protected void fillData(VideoEntity obj, int position) {
        Logger.d(obj.getUrl());
        mPlayer.setUpLazy(obj.getUrl(), true, null, null, obj.getName());
//增加title
        mPlayer.getTitleTextView().setVisibility(View.GONE);
//设置返回键
        mPlayer.getBackButton().setVisibility(View.GONE);
//设置全屏按键功能
        mPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.startWindowFullscreen(mContext, false, true);
            }
        });
//防止错位设置
        mPlayer.setPlayTag(VideoConstant.PLAYING_TAG);
        mPlayer.setPlayPosition(position);
        mPlayer.seekTo(6000);
//是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        mPlayer.setAutoFullWithSize(true);
//音频焦点冲突时是否释放
        mPlayer.setReleaseWhenLossAudio(false);
//全屏动画
        mPlayer.setShowFullAnimation(true);
//小屏时不触摸滑动
        mPlayer.setIsTouchWiget(false);
    }
}
