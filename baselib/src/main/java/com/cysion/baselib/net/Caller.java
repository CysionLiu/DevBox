package com.cysion.baselib.net;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by cysion on 2018\6\25 0025.
 * 使用单例对象前，必须调用inject方法
 * 当感觉本Caller不合适时，可仿照再建一个类
 */

public class Caller implements ICall {

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private Map<String, String> mHeaders;
    private static volatile Caller instance;

    private Caller() {
    }

    public static synchronized Caller obj() {
        if (instance == null) {
            instance = new Caller();
        }
        return instance;
    }

    @Override
    public void inject(String baseUrl, final AInjector aAInjector) {
        if (mOkHttpClient != null) {
            return;
        }
        mOkHttpClient = CallManager.obj().getOkBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (aAInjector==null) {
                    return chain.proceed(request);
                }
                //加入通用header
                mHeaders = aAInjector.headers();
                if (mHeaders != null && mHeaders.size() > 0) {
                    Request.Builder builder = request.newBuilder();
                    Set<String> keys = mHeaders.keySet();
                    for (String key : keys) {
                        builder.addHeader(key, mHeaders.get(key));
                    }
                    request = builder.build();
                }
                return chain.proceed(request);
            }
        }).build();
        mRetrofit = CallManager.obj().getReBuilder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .build();
    }

    @Override
    public <T> T load(Class<T> clazz) {
        if (mRetrofit == null) {
            try {
                throw new Exception("首次使用前，需调用init()方法");
            } catch (Exception aE) {
                aE.printStackTrace();
            }
        }
        return mRetrofit.create(clazz);
    }

    @Override
    public OkHttpClient getClient() {
        return mOkHttpClient;
    }
}
