package com.cysion.train.api;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
