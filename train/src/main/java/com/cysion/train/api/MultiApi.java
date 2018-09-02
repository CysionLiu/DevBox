package com.cysion.train.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MultiApi {

    //获得首页所有数据
    @GET("content/?l=api.home")
    Call<String> getHomeAllData(@Query("json") int json, @Query("appid") int appid);

    //获取基础说明配置
    @GET("content/?l=api.hotline")
    Call<String> getConfig(@Query("json") int json, @Query("appid") int appid);

    //获取定制方案列表
    @GET("content/?l=api.made")
    Call<String> getPlans(@Query("json") int json, @Query("appid") int appid);

    //得到海报
    @GET("content/?l=api.poster")
    Call<String> getPoster(@Query("json") int json, @Query("appid") int appid,
                           @Query("uid") String uid, @Query("id") String id);

    //提交定制方案
    @GET("content/?l=api.publish")
    Call<String> publishPlan(@QueryMap Map<String, String> params);
}
