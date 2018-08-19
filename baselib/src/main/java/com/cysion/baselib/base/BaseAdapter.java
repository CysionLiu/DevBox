package com.cysion.baselib.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cysion.baselib.listener.OnTypeClickListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    protected List<T> mEntities;
    protected Context mContext;
    protected OnTypeClickListener mOnTypeClickListener;

    public BaseAdapter(List<T> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        mEntities = aEntities;
        if (mEntities == null) {
            mEntities = new ArrayList<>();
        }
        mContext = aContext;
        mOnTypeClickListener = aOnTypeClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).bindData(mContext, mOnTypeClickListener, mEntities.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mEntities == null ? 0 : mEntities.size();
    }

    public void addEntities(List<T> aEntities) {
        if (mEntities != null) {
            mEntities.addAll(aEntities);
        } else {
            mEntities = aEntities;
        }
    }

    public void setEntities(List<T> aEntities) {
        if (mEntities != null) {
            mEntities.clear();
            mEntities.addAll(aEntities);
        } else {
            mEntities = aEntities;
        }
    }

    @NonNull
    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);
}
