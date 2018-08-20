package com.cysion.train.holder.style;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.train.R;
import com.cysion.train.entity.StyleBean;

import butterknife.BindView;

public class StyleHolder extends BaseViewHolder<StyleBean> {
    @BindView(R.id.iv_home_style)
    ImageView mIvHomeStyle;
    @BindView(R.id.tv_home_style)
    TextView mTvHomeStyle;

    public StyleHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void fillData(StyleBean obj, int position) {
        mTvHomeStyle.setText(obj.getName());
        Glide.with(mContext).load(obj.getTop()).into(mIvHomeStyle);
    }
}
