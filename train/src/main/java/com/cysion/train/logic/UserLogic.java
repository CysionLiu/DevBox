package com.cysion.train.logic;

import android.text.TextUtils;

import com.cysion.baselib.Box;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.api.UserApi;
import com.cysion.train.helper.UserHelper;
import com.cysion.train.simple.UserCaller;
import com.cysion.train.utils.MyJsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
        UserCaller.obj().load(UserApi.class)
                .login(Constant.COMMON_QUERY_APPID, mobile, smsCode,
                        Constant.COMMON_APP_TYPE)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = MyJsonUtil.obj().handleCommon(body, aPureListener);
                            if (jsonObject == null) {
                                return;
                            }
                            String uid = jsonObject.optString("uid");
                            String session = jsonObject.getString("session");
                            if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(session)) {
                                aPureListener.dont(404, Box.str(R.string.str_invalid_data));
                            } else {
                                UserHelper.obj().uid = uid;
                                UserHelper.obj().session = session;
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


}
