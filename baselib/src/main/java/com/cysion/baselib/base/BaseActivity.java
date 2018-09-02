package com.cysion.baselib.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbind;
    protected Activity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
            mUnbind = ButterKnife.bind(this);
        }
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbind != null && mUnbind != Unbinder.EMPTY) {
            mUnbind.unbind();
        }
        mUnbind = null;
        EventBus.getDefault().unregister(this);
    }

    //仅用来声明默认接收方法的，实际不应该接收任何消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fromEventBus(Application app) {
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void initData(){}
}
