package com.cysion.train.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.holder.OrgTrainHolder;
import com.cysion.train.holder.train.CollectTrainHolder;
import com.cysion.train.holder.train.RecommandTrainHolder;
import com.cysion.train.holder.train.TrainHolder;

import java.util.List;

public class TrainAdapter extends BaseAdapter<TrainCourseBean> {



    public TrainAdapter(List<TrainCourseBean> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case Constant.MAIN_LIST:
                return new TrainHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_train, parent, false)
                );
            case Constant.HOME_LIST:
                return new TrainHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_train_opt, parent, false)
                );
            case Constant.ORG_LIST:
                return new OrgTrainHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_train_org, parent, false)
                );
            case Constant.RECOMMAND_LIST:
                return new RecommandTrainHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_train_recommand, parent, false)
                );
            case Constant.COLLECT_LIST:
                return new CollectTrainHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_train_col, parent, false)
                );
        }
        return null;

    }

    @Override
    public int getItemViewType(int position) {
        return mEntities.get(position).getLocalType();
    }
}
