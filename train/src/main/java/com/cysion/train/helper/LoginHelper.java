package com.cysion.train.helper;

import android.app.Activity;
import android.content.Intent;

import com.cysion.train.activity.LoginActivity;

public class LoginHelper {
    private static volatile LoginHelper instance;

    private LoginHelper() {

    }

    public static synchronized LoginHelper obj() {
        if (instance == null) {
            instance = new LoginHelper();
        }
        return instance;
    }

    public boolean toLoginPage(Activity aActivity) {
        if (!UserCache.obj().isLogin()) {
            Intent myIntent = new Intent(aActivity, LoginActivity.class);
            aActivity.startActivity(myIntent);
            return true;
        }
        return false;
    }
}
