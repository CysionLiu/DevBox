package com.cysion.train.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.R;
import com.cysion.train.entity.ExpertBean;
import com.cysion.train.holder.expert.ExpertHolder;

import java.util.List;

import butterknife.BindView;

public class ExpertAdapter extends BaseAdapter<ExpertBean> {

    public ExpertAdapter(List<ExpertBean> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpertHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_experts, parent, false)
        );
    }
}
