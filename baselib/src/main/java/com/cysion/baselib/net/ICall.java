package com.cysion.baselib.net;

import okhttp3.OkHttpClient;

public interface ICall {
    void inject(String baseUrl, AInjector aAInjector);

    <T> T load(Class<T> clazz);

    OkHttpClient getClient();
}
