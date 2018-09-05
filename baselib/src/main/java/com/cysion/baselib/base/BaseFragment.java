package com.cysion.baselib.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    protected View mRootView;
    protected boolean dataLoaded = false;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this,mRootView);
        initViews();
        initData();
        return mRootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        mUnbinder = null;
        EventBus.getDefault().unregister(this);
    }


    //设置页面对用户可见状态，需要时，子类重写。适用于Viewpager
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //可见，且数据未加载过
        if (isVisibleToUser && !dataLoaded) {
            dataLoaded = true;
            lazyLoad();
        } else if (isVisibleToUser) {//可见，数据加载过
            visibleAgain();
        } else {
            hideAgain();
        }
    }

    protected void hideAgain() {
        Logger.d("不可见--->" + getClass().getSimpleName());
    }

    //仅用来声明默认接收方法的，实际不应该接收任何消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fromEventBus(Application app) {
    }

    //再次可见，对应于lazyload的首次可见，此后，本方法在每次页面用户可见时，都被调用
    protected void visibleAgain() {
        Logger.d("可见--->" + this.getClass().getName());
    }

    //数据懒加载,即用户首次看到本页面时调用，只调用一次
    protected void lazyLoad() {
        Logger.d("懒加载：" + this.getClass().getName());
    }

    //针对add方式添加
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //为方便调试找寻页面添加,add方式添加
        if (!hidden) {
            Logger.d("可见--->" + getClass().getSimpleName());
        } else {
            Logger.d("不可见--->" + getClass().getSimpleName());
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected  void initData(){};
}
