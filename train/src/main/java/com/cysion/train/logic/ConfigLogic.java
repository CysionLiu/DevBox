package com.cysion.train.logic;

import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.net.Caller;
import com.cysion.train.Constant;
import com.cysion.train.api.MultiApi;
import com.cysion.train.entity.ConfigBean;
import com.cysion.train.utils.MyJsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigLogic {

    public ConfigBean sConfigBean;

    private static volatile ConfigLogic instance;

    private ConfigLogic() {

    }

    public static synchronized ConfigLogic obj() {
        if (instance == null) {
            instance = new ConfigLogic();
        }
        return instance;
    }

    public void getConfig(final PureListener<String> aPureListener) {
        Caller.obj().load(MultiApi.class)
                .getConfig(Constant.COMMON_QUERY_JSON, Constant.COMMON_QUERY_APPID)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject obj = MyJsonUtil.obj().handleCommonObj(body, aPureListener);
                            if (obj == null) {
                                return;
                            }
                            ConfigBean configBean = MyJsonUtil.obj().gson().fromJson(obj.toString(), ConfigBean.class);
                            sConfigBean = configBean;
                            aPureListener.done(body);

                        } catch (JSONException aE) {
                            aE.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

    }
}
