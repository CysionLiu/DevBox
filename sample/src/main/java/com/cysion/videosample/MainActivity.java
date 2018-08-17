package com.cysion.videosample;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

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
        setContentView(R.layout.activity_main);
        changeStatusBarTextColor(true);
    }

    public void simplePlay(View view) {
        Logger.d("go to simple play");
        Intent myIntent = new Intent(MainActivity.this, SimplePlayActivity.class);
        startActivity(myIntent);
    }

    public void listPlay(View view) {
        Logger.d("go to list play");
        Intent myIntent = new Intent(MainActivity.this, ListVideoActivity.class);
        startActivity(myIntent);
    }

    private void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setTransparent(this);
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            }else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }else{
        }
    }

}
