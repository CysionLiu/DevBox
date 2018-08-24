package com.cysion.train.simple;

import com.cysion.baselib.net.AInjector;
import com.cysion.baselib.net.CallManager;
import com.cysion.baselib.net.Caller;
import com.cysion.baselib.net.ICall;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class UserCaller implements ICall {

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private Map<String, String> mHeaders;

    private static volatile UserCaller instance;

    private UserCaller() {

    }

    public static synchronized UserCaller obj() {
        if (instance == null) {
            instance = new UserCaller();
        }
        return instance;
    }

    @Override
    public void inject(String baseUrl, final AInjector aAInjector) {
        if (mOkHttpClient != null) {
            return;
        }
        mOkHttpClient = Caller.obj().getClient();
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
