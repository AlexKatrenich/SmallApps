package com.katrenich.alex.smartiwaycopy;

import android.app.Application;

import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;

public class App extends Application {
    private static App instance;
    private static UserInfo sUserInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sUserInfo = UserInfo.getInstance();
    }

    public static App getInstance() {
        return instance;
    }

    public static UserInfo getUserInfo(){ return sUserInfo; }
}
