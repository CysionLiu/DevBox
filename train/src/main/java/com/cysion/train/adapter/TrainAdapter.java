package com.cysion.train.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.R;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.holder.train.TrainHolder;

import java.util.List;

public class TrainAdapter extends BaseAdapter<TrainCourseBean> {

    public TrainAdapter(List<TrainCourseBean> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrainHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_train, parent, false)
        );

    }
}
