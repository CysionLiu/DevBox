package com.cysion.train.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.image.GlideRoundTransform;
import com.cysion.train.R;
import com.cysion.train.entity.TrainCourseBean;

import butterknife.BindView;

public class OrgTrainHolder extends BaseViewHolder<TrainCourseBean> {

    @BindView(R.id.iv_train_top)
    ImageView mIvTrainTop;
    @BindView(R.id.tv_style_tag)
    TextView mTvStyleTag;
    @BindView(R.id.tv_train_name)
    TextView mTvTrainName;
    @BindView(R.id.tv_train_address)
    TextView mTvTrainAddress;
    @BindView(R.id.tv_train_time)
    TextView mTvTrainTime;

    public OrgTrainHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void fillData(TrainCourseBean obj, int position) {
        Glide.with(mContext).load(obj.getTop()).transform(new GlideRoundTransform(mContext)).into(mIvTrainTop);
        mTvStyleTag.setText(obj.getStyle());
        mTvTrainName.setText(obj.getName());
        mTvTrainAddress.setText(obj.getCity());
        mTvTrainTime.setText(obj.getStarts() + " " + obj.getWeek());
    }
}
