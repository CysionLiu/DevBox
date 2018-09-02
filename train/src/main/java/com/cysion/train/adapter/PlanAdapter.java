package com.cysion.train.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.R;
import com.cysion.train.entity.PlanEntity;

import java.util.List;

import butterknife.BindView;

public class PlanAdapter extends BaseAdapter<PlanEntity> {


    public PlanAdapter(List<PlanEntity> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_plan, parent, false));
    }

    class InnerHolder extends BaseViewHolder<PlanEntity> {

        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.fl_item_box)
        FrameLayout mFlItemBox;

        public InnerHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void fillData(PlanEntity obj, int position) {
            mTvName.setText(obj.getName());
        }
    }
}
