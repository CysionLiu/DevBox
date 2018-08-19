package com.cysion.train.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MultiApi {

    //获得首页所有数据
    @GET("content/?l=api.home")
    Call<String> getHomeAllData(@Query("json") int json, @Query("appid") int appid);

}
