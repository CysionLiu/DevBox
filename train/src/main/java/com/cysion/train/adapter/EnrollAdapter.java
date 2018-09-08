package com.cysion.train.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.image.GlideRoundTransform;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.R;
import com.cysion.train.entity.EnrollEntity;
import com.cysion.train.entity.TrainCourseBean;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;

public class EnrollAdapter extends BaseAdapter<EnrollEntity> {

    public static final int TRAIN = 51401;
    public static final int CONSULT = 51402;

    public EnrollAdapter(List<EnrollEntity> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_train_enroll_list, parent, false)
        );
    }

    class InnerHolder extends BaseViewHolder<EnrollEntity> {

        @BindView(R.id.fl_item_box)
        FrameLayout mFlItemBox;
        @BindView(R.id.tv_order_num)
        TextView mTvOrderNum;
        @BindView(R.id.iv_train_top)
        ImageView mIvTrainTop;
        @BindView(R.id.tv_style_tag)
        TextView mTvStyleTag;
        @BindView(R.id.tv_train_name)
        TextView mTvTrainName;
        @BindView(R.id.tv_train_time_address)
        TextView mTvTrainTimeAddress;
        @BindView(R.id.tv_price_ext)
        TextView mTvPriceExt;
        @BindView(R.id.tv_train_price)
        TextView mTvTrainPrice;
        @BindView(R.id.tv_order_time)
        TextView mTvOrderTime;
        @BindView(R.id.tv_consult)
        TextView mTvConsult;

        @BindView(R.id.tv_order_count)
        TextView mTvOrderCount;

        public InnerHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void fillData(final EnrollEntity aEnrollEntity, final int position) {
            mTvConsult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnTypeClickListener.onClicked(aEnrollEntity,position,CONSULT);

                }
            });
            mFlItemBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnTypeClickListener.onClicked(aEnrollEntity,position,TRAIN);
                }
            });
            mTvOrderNum.setText(aEnrollEntity.getGoods());
            String cdate = aEnrollEntity.getCdate();
            long cd = Long.valueOf(cdate) * 1000;
            mTvOrderTime.setText(TimeUtils.millis2String(cd, new SimpleDateFormat("yyyy-MM-dd")));
            mTvOrderCount.setText("数量：" + aEnrollEntity.getAmount() + "张");
            //价格

            String priceStr = "¥" + aEnrollEntity.getNow_price();
            mTvTrainPrice.setText(priceStr);

            TrainCourseBean obj = aEnrollEntity.getMeeting();
            if (obj == null) {
                return;
            }
            Glide.with(mContext).load(obj.getTop()).transform(new GlideRoundTransform(mContext))
                    .placeholder(R.mipmap.place_list).into(
                    mIvTrainTop);

            mTvTrainName.setText(obj.getName());
            //时间地点
            String area = obj.getCity();
            String startTime = obj.getStarts();
            if (TextUtils.isEmpty(startTime) || startTime.length() > 5) {
                startTime = obj.getStart();
            }
            String area_time = startTime + " · " + area;
            mTvTrainTimeAddress.setText(area_time);

            mTvTrainPrice.setText(priceStr);
            mTvStyleTag.setText(obj.getStyle());
        }
    }
}
