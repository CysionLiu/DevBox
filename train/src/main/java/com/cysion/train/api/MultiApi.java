package com.cysion.train.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MultiApi {

    //获得首页所有数据
    @GET("content/?l=api.home")
    Call<String> getHomeAllData(@Query("json") int json, @Query("appid") int appid);

    //获取基础说明配置
    @GET("content/?l=api.hotline")
    Call<String> getConfig(@Query("json") int json, @Query("appid") int appid);

    @GET("content/?l=api.poster")
    Call<String> getPoster(@Query("json") int json, @Query("appid") int appid,
                           @Query("uid") String uid, @Query("id") String id);
}
