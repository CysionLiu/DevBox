package com.cysion.videosample.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api1 {

    @GET("cultivate/content/?l=api.expert")
    Call<String> getExperts(@Query("json") String json,@Query("appid") String appid);
}
