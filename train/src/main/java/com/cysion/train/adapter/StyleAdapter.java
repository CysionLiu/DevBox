package com.cysion.train.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.R;
import com.cysion.train.entity.StyleBean;
import com.cysion.train.holder.style.StyleHolder;

import java.util.List;

public class StyleAdapter extends BaseAdapter<StyleBean> {


    public StyleAdapter(List<StyleBean> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StyleHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_style, parent, false)
        );
    }
}
