package com.cysion.videosample.api;

import com.cysion.videosample.base.BaseEntity;
import com.cysion.videosample.entity.ExpertEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("cultivate/content/?l=api.expert")
    Call<BaseEntity<List<ExpertEntity>>> getExpertList();

    @GET("cultivate/content/?l=api.expert_desc")
    Call<BaseEntity<ExpertEntity>> getExpertDetail(@Query("id") String id);

}
