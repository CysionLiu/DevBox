package com.cysion.train.logic;

import android.text.TextUtils;

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
import com.cysion.train.entity.PlanEntity;
import com.cysion.train.fragment.HomeFragment;
import com.cysion.train.utils.MyJsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultiLogic {


    private static volatile MultiLogic instance;

    private MultiLogic() {
    }

    public static synchronized MultiLogic obj() {
        if (instance == null) {
            instance = new MultiLogic();
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

    public void getPoster(String mid, final PureListener<String> aPureListener) {
        if (!NetworkUtils.isConnected()) {
            aPureListener.dont(404, Box.str(R.string.str_no_net));
        }
        Caller.obj().load(MultiApi.class).getPoster(Constant.COMMON_QUERY_JSON
                , Constant.COMMON_QUERY_APPID, UserCache.obj().getUid(), mid).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    String imgUrl = jsonObject.optString("data");
                    if (TextUtils.isEmpty(imgUrl)) {
                        aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                        return;
                    }
                    aPureListener.done(imgUrl);
                } catch (Exception aE) {
                    aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                aPureListener.dont(404, t.getMessage());
            }
        });
    }

    public void getPlans(final PureListener<List<PlanEntity>> aPureListener) {
        if (!NetworkUtils.isConnected()) {
            aPureListener.dont(404, Box.str(R.string.str_no_net));
        }
        Caller.obj().load(MultiApi.class)
                .getPlans(Constant.COMMON_QUERY_JSON, Constant.COMMON_QUERY_APPID)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject obj1 = MyJsonUtil.obj().handleCommonObj(body, aPureListener);
                            if (obj1 == null) return;
                            JSONArray list = obj1.optJSONArray("plan");
                            String jsonList = list.toString();
                            Logger.d(jsonList);
                            List<PlanEntity> ps = new Gson().fromJson(jsonList, new TypeToken<List<PlanEntity>>() {
                            }.getType());
                            aPureListener.done(ps);
                        } catch (Exception aE) {
                            aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

    public void publishPlan(String planId, String name, String phone, String company, String area,
                            String remark, final PureListener<Integer> aPureListener) {
        if (!NetworkUtils.isConnected()) {
            aPureListener.dont(404, Box.str(R.string.str_no_net));
        }
        Map<String, String> pa = new HashMap<>();
        pa.put("json", Constant.COMMON_QUERY_JSON + "");
        pa.put("appid", Constant.COMMON_QUERY_APPID + "");
        pa.put("uid", UserCache.obj().getUid());
        pa.put("title_id", planId);
        pa.put("name", name);
        pa.put("phone", phone);
        pa.put("area", area);
        pa.put("comname", company);
        pa.put("remark", remark);
        Caller.obj().load(MultiApi.class)
                .publishPlan(pa)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int data = jsonObject.optInt("data");
                            if (data > 0) {
                                aPureListener.done(data);
                                return;
                            }
                            aPureListener.dont(404, Box.str(R.string.str_submit_fail));
                        } catch (Exception aE) {
                            aPureListener.dont(404, Box.str(R.string.str_submit_fail));
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        aPureListener.dont(404, t.getMessage());
                    }
                });

    }
}
