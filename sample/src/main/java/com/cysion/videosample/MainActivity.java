package com.cysion.videosample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.cysion.baselib.net.AInjector;
import com.cysion.baselib.net.Caller;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.videosample.api.Api1;
import com.cysion.videosample.entity.ExpertEntity;
import com.cysion.videosample.entity.ExpertMapEntity;
import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Caller.obj().inject("https://trade.5dev.cn/", new AInjector() {
            @Override
            public Map<String, String> headers() {
                return new HashMap<>();
            }
        });
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return true;
            }
        });
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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


    public void numParse(View view) {
        Caller.obj().load(Api1.class).getExperts("1", "13")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Logger.d(response.body());
                        String json = response.body();
                        ExpertMapEntity expertMapEntity = new Gson().fromJson(json, ExpertMapEntity.class);
                        Map<String, ExpertEntity> data = expertMapEntity.getData();
                        Set<String> strings = data.keySet();
                        List<ExpertEntity> tmp = new ArrayList<>();
                        for (String string : strings) {
                            tmp.add(data.get(string));
                        }
                        expertMapEntity.setRevisedData(tmp);
                        for (int i = 0; i < tmp.size(); i++) {
                            Logger.d(new Gson().toJson(tmp.get(i)));
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Logger.d(t.getMessage());

                    }
                });

    }
}
