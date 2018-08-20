package com.cysion.train.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cysion.train.R;
import com.cysion.train.entity.HomeTopBean;

import java.util.List;

public class HomeTopPageAdapter extends PagerAdapter {
    private Context mContext;
    private List<HomeTopBean> mHomeTopBeans;

    public HomeTopPageAdapter(Context aContext, List<HomeTopBean> aHomeTopBeans) {
        mContext = aContext;
        mHomeTopBeans = aHomeTopBeans;
    }

    @Override
    public int getCount() {
        return mHomeTopBeans == null ? 0 : mHomeTopBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * 返回要显示的view,即要显示的视图
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vp_home_top, null);
        ImageView imgTop = view.findViewById(R.id.iv_home_top);
        com.orhanobut.logger.Logger.d(mHomeTopBeans.get(position).getTop());
        Glide.with(mContext).load(mHomeTopBeans.get(position).getTop()).into(imgTop);
        container.addView(view);    //这一步很重要
        return view;
    }

    /**
     * 销毁条目
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
