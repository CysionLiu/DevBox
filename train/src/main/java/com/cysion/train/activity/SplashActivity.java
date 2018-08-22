package com.cysion.train.activity;

import android.webkit.WebView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.train.R;


public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        WebView web = findViewById(R.id.web);
        web.loadUrl("https://trade.5dev.cn/cultivate/main/?l=details&id=7");
    }

    @Override
    protected void initData() {

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent myIntent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(myIntent);
//                finish();
//            }
//        }, 200);

    }
}
