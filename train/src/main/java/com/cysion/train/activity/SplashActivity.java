package com.cysion.train.activity;

import android.content.Intent;
import android.os.Handler;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.train.R;


public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        }, 1000);

    }
}
