package com.cysion.train.holder.train;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.image.GlideRoundTransform;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.entity.TrainCourseBean;

import java.util.List;

import butterknife.BindView;

public class TrainHolder extends BaseViewHolder<TrainCourseBean> {

    @BindView(R.id.iv_train_top)
    ImageView mIvTrainTop;
    @BindView(R.id.tv_train_name)
    TextView mTvTrainName;
    @BindView(R.id.tv_train_tags)
    TextView mTvTrainTags;
    @BindView(R.id.tv_train_time_address)
    TextView mTvTrainTimeAddress;
    @BindView(R.id.tv_train_price)
    TextView mTvTrainPrice;
    @BindView(R.id.tv_price_ext)
    TextView mTvTrainExt;
    @BindView(R.id.tv_style_tag)
    TextView mTvTrainTag;

    public TrainHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void fillData(TrainCourseBean obj, int position) {
        if (obj == null) {
            return;
        }
        if (obj.getLocalType() == Constant.MAIN_LIST) {
            Glide.with(mContext).load(obj.getTop())
                    .placeholder(R.mipmap.place_list).transform(new GlideRoundTransform(mContext)).into(
                    mIvTrainTop);
        } else {
            Glide.with(mContext).load(obj.getTop()).into(
                    mIvTrainTop);
        }

        mTvTrainName.setText(obj.getName());
        //标签
        String tagStr = "";
        List<TrainCourseBean.TagsBean> tags = obj.getTags();
        if (tags != null) {
            for (TrainCourseBean.TagsBean tag : tags) {
                tagStr += " · " + tag.getName();
            }
        }
        if (tagStr.length() > 3) {
            tagStr = tagStr.substring(3);
        }
        mTvTrainTags.setText(tagStr);
        //时间地点
        String area = obj.getCity();
        String startTime = obj.getStarts();
        if (TextUtils.isEmpty(startTime) || startTime.length() > 5) {
            startTime = obj.getStart();
        }
        String area_time = startTime + " · " + area;
        mTvTrainTimeAddress.setText(area_time);
        //价格
        TrainCourseBean.PriceBean price = obj.getPrice();
        String priceStr = "";
        if (price != null) {
            priceStr = "¥" + price.getMin();
            mTvTrainExt.setText(price.getPrice_ext());
        }
        mTvTrainPrice.setText(priceStr);
        mTvTrainTag.setText(obj.getStyle());
    }

}
