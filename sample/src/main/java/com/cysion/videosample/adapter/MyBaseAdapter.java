package com.cysion.videosample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cysion.videosample.base.BaseViewHolder;
import com.cysion.videosample.base.OnTypeClickListener;

import java.util.List;

public abstract class MyBaseAdapter<T> extends RecyclerView.Adapter {

    protected List<T> mEntities;
    protected Context mContext;
    protected OnTypeClickListener mOnTypeClickListener;

    public MyBaseAdapter(List<T> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        mEntities = aEntities;
        mContext = aContext;
        mOnTypeClickListener = aOnTypeClickListener;
    }

    @NonNull
    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).bindData(mContext,mOnTypeClickListener, mEntities.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mEntities == null ? 0 : mEntities.size();
    }
}
