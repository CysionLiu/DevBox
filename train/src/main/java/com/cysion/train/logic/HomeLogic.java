package com.cysion.train.logic;

import com.blankj.utilcode.util.NetworkUtils;
import com.cysion.baselib.Box;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.net.Caller;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.api.MultiApi;
import com.cysion.train.entity.ExpertBean;
import com.cysion.train.entity.HomeAllDataBean;
import com.cysion.train.entity.HomeBean;
import com.cysion.train.entity.HomeDataBean;
import com.cysion.train.entity.TrainOrgBean;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                    HomeAllDataBean homeAllDataBean = new Gson().fromJson(body, HomeAllDataBean.class);
                    HomeDataBean data = homeAllDataBean.getData();
                    //校正home数据
                    Map<String, HomeBean> home = data.getHome();
                    if (home != null && home.size() > 0) {
                        Set<String> strings = home.keySet();
                        List<HomeBean> tmp = new ArrayList<>();
                        for (String string : strings) {
                            tmp.add(home.get(string));
                        }
                        data.setRevisedHome(tmp);
                    }
                    //校正专家数据
                    Map<String, ExpertBean> expert = data.getExpert();
                    if (expert != null && expert.size() > 0) {
                        Set<String> strings = expert.keySet();
                        List<ExpertBean> tmp = new ArrayList<>();
                        for (String string : strings) {
                            tmp.add(expert.get(string));
                        }
                        data.setRevisedExpert(tmp);
                    }
                    //校正培训机构数据
                    Map<String, TrainOrgBean> course = data.getTrain();
                    if (course != null && course.size() > 0) {
                        Set<String> strings = course.keySet();
                        List<TrainOrgBean> tmp = new ArrayList<>();
                        for (String string : strings) {
                            tmp.add(course.get(string));
                        }
                        data.setRevisedTrain(tmp);
                    }
                    aHashMapPureListener.done(data);
                } catch (Exception aE) {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                aHashMapPureListener.dont(404, t.getMessage());
            }
        });
    }
}
