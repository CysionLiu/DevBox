package com.cysion.videosample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.cysion.baselib.utils.ShowUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return true;
            }
        });
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);
        ShowUtil.darkAndWhite(this, true);
    }

    public void simplePlay(View view) {
        ActivityUtils.finishAllActivities();
        Logger.d("go to simple play");
        Intent myIntent = new Intent(MainActivity.this, SimplePlayActivity.class);
        startActivity(myIntent);
    }

    public void listPlay(View view) {
        Logger.d("go to list play");
        Intent myIntent = new Intent(MainActivity.this, ListVideoActivity.class);
        startActivity(myIntent);
    }


}
