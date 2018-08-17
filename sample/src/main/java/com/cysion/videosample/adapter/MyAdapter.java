package com.cysion.videosample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cysion.videosample.R;
import com.cysion.videosample.adapter.holder.VideoHolder;
import com.cysion.videosample.base.OnTypeClickListener;
import com.cysion.videosample.entity.VideoEntity;

import java.util.List;

public class MyAdapter extends MyBaseAdapter<VideoEntity> {

    public MyAdapter(List<VideoEntity> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_video_play, parent, false));
    }
}
