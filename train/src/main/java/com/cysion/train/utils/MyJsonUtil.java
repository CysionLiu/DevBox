package com.cysion.train.utils;

import android.support.annotation.Nullable;

import com.cysion.baselib.Box;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyJsonUtil {

    private Gson gson;
    private static volatile MyJsonUtil instance;

    private MyJsonUtil() {
        gson = new Gson();
    }

    public static synchronized MyJsonUtil obj() {
        if (instance == null) {
            instance = new MyJsonUtil();
        }
        return instance;
    }

    //校验后，得到data数据,{}
    @Nullable
    public JSONObject handleCommonObj(String aBody, PureListener aPureListener) throws JSONException {
        JSONObject jsonObject = new JSONObject(aBody);
        int status = jsonObject.optInt("status");
        if (status != Constant.STATUS_SUCCESS) {
            aPureListener.dont(status, jsonObject.optString("msg"));
            return null;
        }
        JSONObject obj1 = jsonObject.optJSONObject("data");
        if (obj1 == null) {
            aPureListener.dont(404, Box.str(R.string.str_invalid_data));
            return null;
        }
        return obj1;
    }

    //校验后，得到data数据,[]
    @Nullable
    public JSONArray handleCommonArray(String aBody, PureListener aPureListener) throws JSONException {
        JSONObject jsonObject = new JSONObject(aBody);
        int status = jsonObject.optInt("status");
        if (status != Constant.STATUS_SUCCESS) {
            aPureListener.dont(status, jsonObject.optString("msg"));
            return null;
        }
        JSONArray data = jsonObject.optJSONArray("data");
        if (data == null) {
            aPureListener.dont(404, Box.str(R.string.str_invalid_data));
            return null;
        }
        return data;
    }

    public Gson gson() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson;
    }

}
