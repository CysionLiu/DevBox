package com.cysion.baselib.net;

import com.cysion.baselib.Box;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//不能直接用来进行网络请求，见Caller类
public class CallManager {

    private static volatile CallManager instance;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    private CallManager() {
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(getLevel()))
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS).build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://www.xxxxxxxxxxx.com/")//也就是CallManager的不能直接用
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public static synchronized CallManager obj() {
        if (instance == null) {
            instance = new CallManager();
        }
        return instance;
    }


    private HttpLoggingInterceptor.Level getLevel() {
        if (Box.isDebug()) {
            return HttpLoggingInterceptor.Level.HEADERS;
        }
        return HttpLoggingInterceptor.Level.NONE;
    }

    public OkHttpClient.Builder getOkBuilder() {
        return mOkHttpClient.newBuilder();
    }

    public Retrofit.Builder getReBuilder() {
        return mRetrofit.newBuilder();
    }
}
