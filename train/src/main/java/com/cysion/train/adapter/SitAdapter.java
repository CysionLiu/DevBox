package com.cysion.train.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.R;
import com.cysion.train.entity.SitBean;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

public class SitAdapter extends BaseAdapter<SitBean> {

    public SitAdapter(List<SitBean> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_enroll_sit, parent, false));
    }

    class InnerHolder extends BaseViewHolder<SitBean> {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.iv_add)
        ImageView mIvAdd;
        @BindView(R.id.tv_count)
        TextView mTvCount;
        @BindView(R.id.iv_minus)
        ImageView mIvMinus;
        @BindView(R.id.tv_zhekou)
        TextView mTvZhekou;
        @BindView(R.id.tv_price_now)
        TextView mTvPriceNow;
        @BindView(R.id.tv_price_old)
        TextView mTvPriceOld;
        @BindView(R.id.tv_end_time)
        TextView mTvEndTime;
        @BindView(R.id.tv_desc)
        TextView mTvDesc;
        @BindView(R.id.iv_tri_select_state)
        ImageView mIvTriSelectState;
        @BindView(R.id.fl_item_box)
        FrameLayout mFlItemBox;

        public InnerHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void fillData(final SitBean obj, final int position) {
            //点击事件
            mIvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj.setNativecount(obj.getNativecount() + 1);
                    notifyDataSetChanged();
                    mOnTypeClickListener.onClicked(obj, position, 0);

                }
            });
            mIvMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj.setNativecount(obj.getNativecount() - 1);
                    notifyDataSetChanged();
                    mOnTypeClickListener.onClicked(obj, position, 0);
                }
            });
            //UI设置
            String max_num_str = obj.getNum();
            int max_num = 0;
            if (!TextUtils.isEmpty(max_num_str)) {
                max_num = Integer.valueOf(max_num_str);
            }
            //不同状态
            if (obj.getNativecount() <= 0) {
                mIvMinus.setEnabled(false);
                mIvTriSelectState.setSelected(false);
                mFlItemBox.setEnabled(false);
            } else {
                mIvMinus.setEnabled(true);
                mIvTriSelectState.setSelected(true);
                mFlItemBox.setEnabled(true);
            }
            if (obj.getNativecount() < max_num) {
                mIvAdd.setEnabled(true);
            } else {
                mIvAdd.setEnabled(false);
            }
            Logger.d(obj.getNum());
            //设置值
            mTvCount.setText(obj.getNativecount() + "");
            mTvName.setText(obj.getSitname());
            mTvZhekou.setText("限时" + obj.getZhe() + "折");
            mTvPriceNow.setText("¥" + obj.getNow_price());
            mTvPriceOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
            mTvPriceOld.getPaint().setAntiAlias(true);// 抗锯齿
            mTvPriceOld.setText("¥" + obj.getPrice());
            mTvDesc.setText(obj.getCommon());
            mTvEndTime.setText(obj.getEnd());

        }
    }
}
