package com.cysion.train.logic;

import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.cysion.baselib.Box;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.net.Caller;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.api.UserApi;
import com.cysion.train.entity.CollectEntity;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.helper.UserCache;
import com.cysion.train.simple.UserCaller;
import com.cysion.train.utils.MyJsonUtil;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLogic {
    private static volatile UserLogic instance;

    private UserLogic() {

    }

    public static synchronized UserLogic obj() {
        if (instance == null) {
            instance = new UserLogic();
        }
        return instance;
    }

    public void login(String mobile, String smsCode, final PureListener<String> aPureListener) {
        if (!NetworkUtils.isConnected()) {
            aPureListener.dont(404, Box.str(R.string.str_no_net));
        }
        UserCaller.obj().load(UserApi.class)
                .login(Constant.COMMON_QUERY_APPID, mobile, smsCode,
                        Constant.COMMON_APP_TYPE)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = MyJsonUtil.obj().handleCommonObj(body, aPureListener);
                            if (jsonObject == null) {
                                return;
                            }
                            String uid = jsonObject.optString("uid");
                            String session = jsonObject.getString("session");
                            if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(session)) {
                                aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                            } else {
                                UserCache.obj().setUid(uid);
                                UserCache.obj().setSession(session);
                                aPureListener.done("200");
                            }

                        } catch (JSONException aE) {
                            aE.printStackTrace();
                            aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        aPureListener.dont(404, t.getMessage());
                    }
                });

    }

    public void col(String colId, final PureListener<Integer> aPureListener) {
        if (!NetworkUtils.isConnected()) {
            aPureListener.dont(404, Box.str(R.string.str_no_net));
        }
        Caller.obj().load(UserApi.class)
                .col(Constant.COMMON_QUERY_JSON, Constant.COMMON_QUERY_APPID,
                        UserCache.obj().getUid(), "1", colId)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject obj = new JSONObject(body);
                            int data = obj.optInt("data");
                            //data目前的收藏状态
                            if (data == Constant.COLLECTED_STATE) {
                                aPureListener.done(Constant.COLLECTED_STATE);
                                return;
                            }
                        } catch (JSONException aE) {
                            aPureListener.dont(404, "收藏失败");
                            aE.printStackTrace();
                        }
                        aPureListener.dont(404, "收藏失败");
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        aPureListener.dont(404, "收藏失败");
                    }
                });
    }

    public void decol(String colId, final PureListener<Integer> aPureListener) {
        if (!NetworkUtils.isConnected()) {
            aPureListener.dont(404, Box.str(R.string.str_no_net));
        }
        Caller.obj().load(UserApi.class)
                .decol(Constant.COMMON_QUERY_JSON, Constant.COMMON_QUERY_APPID,
                        UserCache.obj().getUid(), "1", colId)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject obj = new JSONObject(body);
                            int data = obj.optInt("data");
                            if (data == Constant.NOT_COLLECTED_STATE) {
                                aPureListener.done(Constant.NOT_COLLECTED_STATE);
                                return;
                            }
                        } catch (JSONException aE) {
                            aPureListener.dont(404, "取消收藏失败");
                            aE.printStackTrace();
                        }
                        aPureListener.dont(404, "取消收藏失败");
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        aPureListener.dont(404, "取消收藏失败");
                    }
                });
    }

    public void getColList(final PureListener<List<TrainCourseBean>> aPureListener) {
        Caller.obj().load(UserApi.class).getCollects(
                Constant.COMMON_QUERY_JSON, Constant.COMMON_QUERY_APPID, UserCache.obj().getUid()
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                try {
                    JSONArray jsonArray = MyJsonUtil.obj().handleCommonArray(body, aPureListener);
                    if (jsonArray == null) {
                        aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                        return;
                    }
                    String dataList = jsonArray.toString();
                    List<CollectEntity> data = MyJsonUtil.obj().gson()
                            .fromJson(dataList, new TypeToken<List<CollectEntity>>() {
                            }.getType());
                    if (data == null) {
                        aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                        return;
                    }
                    List<TrainCourseBean> t = new ArrayList<>();
                    for (CollectEntity datum : data) {
                        TrainCourseBean data1 = datum.getData();
                        data1.setLocalType(Constant.COLLECT_LIST);
                        t.add(data1);
                    }
                    aPureListener.done(t);
                } catch (JSONException aE) {
                    aE.printStackTrace();
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
