package com.cysion.train.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    //登录
    // appid	Number
    //项目id
    //mobile	Number
    //手机号
    //code	String
    //验证码
    //type	Number
    //来源1为小程序，2为ios，3为安卓
    @GET("v1/app.login")
    Call<String> login(@Query("appid") int appid, @Query("mobile") String mobile,
                       @Query("code") String code, @Query("type") String type);


    //获得用户信息
    @GET("v1/app.user")
    Call<String> getUser(@Query("json") int json, @Query("appid") int appid,
                         @Query("uid") String uid, @Query("session") String session);


    //更新用户信息
    // uid	Number
    //用户id
    //session	Number
    //验证用户有效性的session key
    //username	String
    //用户名
    //avatar	String
    //头像
    //gender	String
    //性别

    @GET("v1/app.update")
    Call<String> updateUser(@Query("json") int json, @Query("appid") int appid,
                            @Query("uid") String uid, @Query("session") String session,
                            @Query("username") String username,
                            @Query("avatar") String avatar, @Query("gender") String gender);

    //收藏会议
    @GET("content/?l=api.collect")
    Call<String> col(@Query("json") int json, @Query("appid") int appid,
                     @Query("uid") String uid, @Query("type") String type,
                     @Query("col_id") String col_id);

    //取消收藏会议
    @GET("content/?l=api.collect")
    Call<String> decol(@Query("json") int json, @Query("appid") int appid,
                       @Query("uid") String uid, @Query("type") String type,
                       @Query("col_id") String col_id);


    //获得收藏列表
    @GET("content/?l=api.mycollect")
    Call<String> getCollects(@Query("json") int json, @Query("appid") int appid, @Query("uid") String uid,
                             @Query("pg") int page);

    @FormUrlEncoded
    @POST("content/?l=api.renew")
    Call<String> updateClientInfo(@FieldMap Map<String, String> param);

    @GET("content/?l=api.stuff")
    Call<String> getClientInfo(@Query("json") int json, @Query("appid") int appid, @Query("uid") String uid);
}
