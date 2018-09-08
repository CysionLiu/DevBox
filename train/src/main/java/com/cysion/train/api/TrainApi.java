package com.cysion.train.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TrainApi {

    //获得培训会议列表
    @GET("content/?l=api.lists")
    Call<String> getTrainList(@Query("json") int json, @Query("appid") int appid,
                              @Query("search_area") String search_area, @Query("search_style") String search_style,
                              @Query("search_period") String search_period, @Query("type") int type, @Query("pg") int pagenum);

    //获得某个会议详情
    @GET("content/?l=api.desc")
    Call<String> getTrain(@Query("json") int json, @Query("appid") int appid, @Query("id") String id, @Query("uid") String uid);


    //获得专家列表
    @GET("content/?l=api.expert")
    Call<String> getExpertList(@Query("json") int json, @Query("appid") int appid);


    //获得某个专家详情
    @GET("content/?l=api.expert_desc")
    Call<String> getExpert(@Query("json") int json, @Query("appid") int appid, @Query("id") String id);

    //获得培训机构列表
    @GET("content/?l=api.train")
    Call<String> getTrainOrgList(@Query("json") int json, @Query("appid") int appid);

    //获得某个机构详情
    @GET("content/?l=api.train_desc")
    Call<String> getTrainOrg(@Query("json") int json, @Query("appid") int appid, @Query("id") String id);

    //获得推荐会议列表[某个会议的]
    @GET("content/?l=api.matic")
    Call<String> getRecommandMeetings(@Query("json") int json, @Query("appid") int appid, @Query("id") String id);

    //获得某个会议详情
    @GET("content/?l=api.give")
    Call<String> getEnrollInfo(@Query("json") int json, @Query("appid") int appid, @Query("id") String id, @Query("uid") String uid);

    //生成订单
    @FormUrlEncoded
    @POST("content/?l=api.sign")
    Call<String> enroll(@FieldMap Map<String, String> params);

    //生成临时订单
    @FormUrlEncoded
    @POST("content/?l=api.createOrder")
    Call<String> tmpEnroll(@FieldMap Map<String, String> params);


    //获得报名列表
    @FormUrlEncoded
    @POST("content/?l=api.myorder")
    Call<String> getEnrollList(@FieldMap Map<String, String> params);
}
