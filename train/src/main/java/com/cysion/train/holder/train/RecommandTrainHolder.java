package com.cysion.train.holder.train;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.train.R;
import com.cysion.train.entity.TrainCourseBean;

import butterknife.BindView;

public class RecommandTrainHolder extends BaseViewHolder<TrainCourseBean> {


    @BindView(R.id.iv_train_top)
    ImageView mIvTrainTop;
    @BindView(R.id.tv_train_name)
    TextView mTvTrainName;
    @BindView(R.id.tv_train_time_address)
    TextView mTvTrainAddress;


    public RecommandTrainHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void fillData(TrainCourseBean obj, int position) {
        Glide.with(mContext).load(obj.getTop()).into(mIvTrainTop);
        mTvTrainName.setText(obj.getName());
        mTvTrainAddress.setText(obj.getStart() + "·" + obj.getCity());
    }
}
