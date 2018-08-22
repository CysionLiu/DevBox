package com.cysion.train.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.R;
import com.cysion.train.entity.UserOptions;

import java.util.List;

import butterknife.BindView;

public class UserOptionAdapter extends BaseAdapter<UserOptions> {


    public UserOptionAdapter(List<UserOptions> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
        super(aEntities, aContext, aOnTypeClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OptionHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_user_option, parent, false)
        );
    }

    class OptionHolder extends BaseViewHolder<UserOptions> {
        @BindView(R.id.tv_option_name)
        TextView mTvOptionName;
        @BindView(R.id.iv_option_icon)
        ImageView mIvOptionIcon;
        @BindView(R.id.tv_option_content)
        TextView mTvOptionContent;

        public OptionHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void fillData(UserOptions obj, int position) {
            mTvOptionName.setText(obj.getName());
            if (obj.getIcon() != 0) {
                mIvOptionIcon.setImageResource(obj.getIcon());
            }
            mTvOptionContent.setText(obj.getMsg1());
        }
    }
}
