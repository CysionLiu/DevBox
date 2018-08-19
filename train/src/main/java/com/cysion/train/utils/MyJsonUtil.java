package com.cysion.train.utils;

import com.google.gson.Gson;

public class MyJsonUtil {

    private static Gson gson;
    private static volatile MyJsonUtil instance;

    private MyJsonUtil() {
        gson = new Gson();
    }

    public static synchronized MyJsonUtil obj() {
        if (instance == null) {
            instance = new MyJsonUtil();
        }
        return instance;
    }

}
