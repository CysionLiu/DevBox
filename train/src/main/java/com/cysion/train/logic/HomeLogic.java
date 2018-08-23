package com.cysion.train.logic;

import com.blankj.utilcode.util.NetworkUtils;
import com.cysion.baselib.Box;
import com.cysion.baselib.cache.ACache;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.net.Caller;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.api.MultiApi;
import com.cysion.train.entity.HomeAllDataBean;
import com.cysion.train.entity.HomeDataBean;
import com.cysion.train.fragment.HomeFragment;
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeLogic {


    private static volatile HomeLogic instance;

    private HomeLogic() {
    }

    public static synchronized HomeLogic obj() {
        if (instance == null) {
            instance = new HomeLogic();
        }
        return instance;
    }

    public void getAllData(final PureListener<HomeDataBean> aHashMapPureListener) {
        if (!NetworkUtils.isConnected()) {
            aHashMapPureListener.dont(404, Box.str(R.string.str_no_net));
        }
        Caller.obj().load(MultiApi.class).getHomeAllData(Constant.COMMON_QUERY_JSON
                , Constant.COMMON_QUERY_APPID).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    if (jsonObject.optInt("status") != Constant.STATUS_SUCCESS) {
                        aHashMapPureListener.dont(404, jsonObject.optString("msg"));
                        return;
                    }
                    JSONObject obj1 = jsonObject.optJSONObject("data");
                    if (obj1 == null) {
                        aHashMapPureListener.dont(404, Box.str(R.string.str_invalid_data));
                        return;
                    }
                    //缓存
                    ACache.get(Box.ctx()).put(HomeFragment.HOME_DATA_CACHE, body);
                    HomeAllDataBean homeAllDataBean = new Gson().fromJson(body, HomeAllDataBean.class);
                    HomeDataBean data = homeAllDataBean.getData();
                    aHashMapPureListener.done(data);
                } catch (Exception aE) {
                    aHashMapPureListener.dont(404, Box.str(R.string.str_invalid_data));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                aHashMapPureListener.dont(404, t.getMessage());
            }
        });
    }
}
