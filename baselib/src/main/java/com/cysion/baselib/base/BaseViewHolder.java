package com.cysion.baselib.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cysion.baselib.listener.OnTypeClickListener;

import butterknife.ButterKnife;

/**
 * Created by xianshang.liu on 2016/10/20.
 * recylerview对应holder基类，用于解耦绑定数据
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private String mPageType;
    protected OnTypeClickListener mOnTypeClickListener;
    public static final int ITEM_CLICK = 0xccc1;
    protected int mPosition;
    protected View mRoot;
    protected Context mContext;

    public String getPageType() {
        return mPageType;
    }

    public void setPageType(String aPageType) {
        mPageType = aPageType;
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
        mRoot = itemView;
        ButterKnife.bind(this,mRoot);
    }

    public final void bindData(Context aContext, OnTypeClickListener aOnTypeClickListener, final T obj, final int position) {
        mOnTypeClickListener = aOnTypeClickListener;
        mContext = aContext;
        if (mOnTypeClickListener == null) {
            mOnTypeClickListener = new OnTypeClickListener() {
                @Override
                public void onClicked(Object obj, int position, int flag) {
                }
            };
        }
        mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTypeClickListener.onClicked(obj, position, ITEM_CLICK);
            }
        });
        fillData(obj, position);
    }

    protected abstract void fillData(T obj, int position);
}
