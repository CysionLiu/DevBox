package com.cysion.train.logic;

import com.blankj.utilcode.util.NetworkUtils;
import com.cysion.baselib.Box;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.net.Caller;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.api.TrainApi;
import com.cysion.train.entity.ExpertBean;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.utils.MyJsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainLogic {
    private static volatile TrainLogic instance;

    private TrainLogic() {

    }

    public static synchronized TrainLogic obj() {
        if (instance == null) {
            instance = new TrainLogic();
        }
        return instance;
    }

    public void getTrainList(final PureListener<List<TrainCourseBean>> aPureListener, String area, String style, String period, int type) {
        if (!NetworkUtils.isConnected()) {
            aPureListener.dont(404, Box.str(R.string.str_no_net));
        }
        Caller.obj().load(TrainApi.class).getTrainList(
                Constant.COMMON_QUERY_JSON, Constant.COMMON_QUERY_APPID,
                area, style, period, type
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                try {
                    JSONObject obj1 = MyJsonUtil.obj().handleCommon(body, aPureListener);
                    if (obj1 == null) return;
                    JSONArray list = obj1.optJSONArray("list");
                    String jsonList = list.toString();
                    Logger.d(jsonList);
                    List<TrainCourseBean> ps = new Gson().fromJson(jsonList, new TypeToken<List<TrainCourseBean>>() {
                    }.getType());
                    aPureListener.done(ps);
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

    public void getExpertDetail(String id, final PureListener<ExpertBean> aPureListener) {
        Caller.obj().load(TrainApi.class)
                .getExpert(Constant.COMMON_QUERY_JSON,
                        Constant.COMMON_QUERY_APPID, id)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = MyJsonUtil.obj().handleCommon(body, aPureListener);
                            if (jsonObject == null) {
                                return;
                            }
                            String jsonList = jsonObject.toString();
                            Logger.d(jsonList);
                            ExpertBean ps = new Gson().fromJson(jsonList, ExpertBean.class);
                            aPureListener.done(ps);
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


    public void getOrgDetail(String id, final PureListener<ExpertBean> aPureListener) {
        Caller.obj().load(TrainApi.class)
                .getTrainOrg(Constant.COMMON_QUERY_JSON,
                        Constant.COMMON_QUERY_APPID, id)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject obj1 = MyJsonUtil.obj().handleCommon(body, aPureListener);
                            if (obj1 == null) {
                                return;
                            }
                            String jsonList = obj1.toString();
                            Logger.d(jsonList);
                            ExpertBean ps = new Gson().fromJson(jsonList, ExpertBean.class);
                            aPureListener.done(ps);
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

    public void getTrainDetail(String id, final PureListener<TrainCourseBean> aPureListener) {
        Caller.obj().load(TrainApi.class)
                .getTrain(Constant.COMMON_QUERY_JSON,
                        Constant.COMMON_QUERY_APPID, id, "")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            if (jsonObject.optInt("status") != Constant.STATUS_SUCCESS) {
                                aPureListener.dont(404, jsonObject.optString("msg"));
                                return;
                            }
                            JSONObject obj1 = jsonObject.optJSONObject("data");
                            if (obj1 == null) {
                                aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                                return;
                            }
                            String jsonList = obj1.toString();
                            Logger.d(jsonList);
                            TrainCourseBean ps = new Gson().fromJson(jsonList, TrainCourseBean.class);
                            aPureListener.done(ps);
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


}
