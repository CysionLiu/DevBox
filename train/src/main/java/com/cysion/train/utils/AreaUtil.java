package com.cysion.train.utils;

import com.cysion.baselib.Box;
import com.cysion.train.entity.AreaBean;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地域解析类
 */
public class AreaUtil {
    private static volatile AreaUtil instance;
    private List<AreaBean> mProvince;
    private Map<String, List<AreaBean>> mCities;
    private Map<String, List<AreaBean>> mCounties;

    private AreaUtil() {
    }

    public static synchronized AreaUtil obj() {
        if (instance == null) {
            instance = new AreaUtil();
        }
        return instance;
    }

    public List<AreaBean> getProvince() {
        if (mProvince != null) {
            return mProvince;
        }
        mProvince = new ArrayList<>();
        InputStream inputStream = null;
        try {
            inputStream = Box.res().getAssets().open("province.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            List<AreaBean> t = MyJsonUtil.obj().gson().fromJson(reader, new TypeToken<List<AreaBean>>() {
            }.getType());
            if (t != null) {
                mProvince = t;
            }
        } catch (Exception aProvince) {
            return new ArrayList<>();
        }
        return mProvince;
    }

    public List<AreaBean> getCity(String provinceId) {
        if (mCities != null) {
            return mCities.get(provinceId);
        }
        mCities = new HashMap<>();
        InputStream inputStream = null;
        try {
            inputStream = Box.res().getAssets().open("city.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            AreaBean.City t = MyJsonUtil.obj().gson().fromJson(reader, AreaBean.City.class);
            if (t != null && t.getCity() != null) {
                mCities = t.getCity();
                return mCities.get(provinceId);
            }
        } catch (Exception aProvince) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    public List<AreaBean> getCounty(String cityId) {
        if (mCounties != null) {
            return mCounties.get(cityId);
        }
        mCounties = new HashMap<>();
        InputStream inputStream = null;
        try {
            inputStream = Box.res().getAssets().open("county.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            AreaBean.County t = MyJsonUtil.obj().gson().fromJson(reader, AreaBean.County.class);
            if (t != null && t.getCity() != null) {
                mCounties = t.getCity();
                return mCounties.get(cityId);
            }
        } catch (Exception aProvince) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
