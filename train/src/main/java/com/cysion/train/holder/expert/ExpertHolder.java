package com.cysion.train.holder.expert;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.train.R;
import com.cysion.train.entity.ExpertBean;

import butterknife.BindView;

public class ExpertHolder extends BaseViewHolder<ExpertBean> {

    @BindView(R.id.iv_expert_top)
    ImageView mIvExpertTop;
    @BindView(R.id.tv_expert_name)
    TextView mTvExpertName;
    @BindView(R.id.tv_expert_work)
    TextView mTvExpertWork;
    @BindView(R.id.tv_expert_head)
    TextView mTvExpertHead;

    public ExpertHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void fillData(ExpertBean obj, int position) {
        if (TextUtils.isEmpty(obj.getTop())) {
            Glide.with(mContext).load(obj.getLogo()).placeholder(R.mipmap.place_org).into(mIvExpertTop);
        } else {
            Glide.with(mContext).load(obj.getTop()).placeholder(R.mipmap.place_expert).into(mIvExpertTop);
        }
        mTvExpertName.setText(obj.getName());
        mTvExpertWork.setText(obj.getWork());
        if (!TextUtils.isEmpty(obj.getHead())) {
            mTvExpertHead.setVisibility(View.VISIBLE);
            mTvExpertHead.setText(obj.getHead());
        } else {
            mTvExpertHead.setVisibility(View.GONE);
        }
    }
}
