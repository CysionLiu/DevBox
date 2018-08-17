package com.cysion.baselib.net;

public interface ICall {
    void inject(String baseUrl, AInjector aAInjector);

    <T> T load(Class<T> clazz);
}
