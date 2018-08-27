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
import java.util.Set;
import java.util.TreeMap;

/**
 * 地域解析类
 */
public class AreaUtil {
    private static volatile AreaUtil instance;
    private List<AreaBean> mProvince;
    private Map<String, List<AreaBean>> mCities;
    private Map<String, List<AreaBean>> mCounties;
    private List<List<AreaBean>> mCityList;
    private List<List<List<AreaBean>>> mCountyList;

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

    //根据省，获取市
    public List<AreaBean> getCity(String provinceId) {
        if (mCities != null) {
            return mCities.get(provinceId);
        }
        mCities = new TreeMap<>();
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

    //获取所有省的市
    public List<List<AreaBean>> getCities() {
        if (mCityList != null) {
            return mCityList;
        }
        //读取城市数据
        getCity("1");
        mCityList = new ArrayList<>();
        Set<String> strings = mCities.keySet();
        for (String string : strings) {
            mCityList.add(mCities.get(string));
        }
        return mCityList;
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

    public List<List<List<AreaBean>>> getCounties() {
        if (mCountyList != null) {
            return mCountyList;
        }
        //读取文件的所有数据，包括省，市，县
        getProvince();
        getCities();
        getCounty("");
        mCountyList = new ArrayList<>();
        int pSize = mProvince.size();
        //遍历省
        for (int i = 0; i < pSize; i++) {
            //某个省里，所有市的[县区集合]
            List<List<AreaBean>> tmp = new ArrayList<>();
            //某个省的市的集合
            List<AreaBean> city = mCities.get(mProvince.get(i).getId());
            if (city==null) {
                continue;
            }
            int cSize = city.size();
            for (int j = 0; j < cSize; j++) {
                //某个城市的县区集合
                List<AreaBean> county = mCounties.get(city.get(j).getId());
                tmp.add(county);
            }
            mCountyList.add(tmp);
        }
        return mCountyList;
    }
}
