package com.cysion.train.logic;

import com.blankj.utilcode.util.NetworkUtils;
import com.cysion.baselib.Box;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.net.Caller;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.api.TrainApi;
import com.cysion.train.entity.EnrollEntity;
import com.cysion.train.utils.MyJsonUtil;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnrollLogic {
    private static volatile EnrollLogic instance;

    private EnrollLogic() {
    }

    public static synchronized EnrollLogic obj() {
        if (instance == null) {
            instance = new EnrollLogic();
        }
        return instance;
    }

    //提交报名信息
    public void enroll(String mid, String contact, String phone, String bill, String billname
            , String billnum, String sitType, String sitnum, String remarks, final PureListener<String> aPureListener) {

        Map<String, String> param = new HashMap<>();
        param.put("appid", Constant.COMMON_QUERY_APPID + "");
        param.put("json", Constant.COMMON_QUERY_JSON + "");
        param.put("uid", UserCache.obj().getUid());
        param.put("id", mid);
        param.put("share_id", UserCache.obj().getUid());
        param.put("bill", bill);
        param.put("bill_name", billname);
        param.put("bill_num", billnum);
        param.put("name", contact);
        param.put("phone", phone);
        param.put("sit", sitType);
        param.put("sit_num", sitnum);
        param.put("remark", remarks);
        Caller.obj().load(TrainApi.class)
                .enroll(param)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Logger.d(response.body());
                        aPureListener.done(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        aPureListener.dont(404, t.getMessage());
                    }
                });
    }

    public void getEnrollList(final PureListener<List<EnrollEntity>> aPureListener, int page) {
        if (!NetworkUtils.isConnected()) {
            aPureListener.dont(404, Box.str(R.string.str_no_net));
            return;
        }
        Map<String, String> pa = new HashMap<>();
        pa.put("uid", UserCache.obj().getUid());
        pa.put("pg", page + "");
        pa.put("appid", Constant.COMMON_QUERY_APPID + "");
        pa.put("json", Constant.COMMON_QUERY_JSON + "");
        Caller.obj().load(TrainApi.class).
                getEnrollList(pa).
                enqueue(new Callback<String>() {
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
                            List<EnrollEntity> data = MyJsonUtil.obj().gson()
                                    .fromJson(dataList, new TypeToken<List<EnrollEntity>>() {
                                    }.getType());
                            if (data == null) {
                                aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                                return;
                            }
                            aPureListener.done(data);
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
