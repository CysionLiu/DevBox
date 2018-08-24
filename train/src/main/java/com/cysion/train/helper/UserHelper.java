package com.cysion.train.helper;

import com.cysion.train.entity.UserEntity;

public class UserHelper {

    //用户的uid和session
    public String uid = "";
    public String session = "";
    public UserEntity mUserEntity;
    private static volatile UserHelper instance;

    private UserHelper() {

    }

    public static synchronized UserHelper obj() {
        if (instance == null) {
            instance = new UserHelper();
        }
        return instance;
    }
}
