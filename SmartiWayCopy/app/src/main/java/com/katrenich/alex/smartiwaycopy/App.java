package com.katrenich.alex.smartiwaycopy;

import android.app.Application;

import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.network.NetworkService;

public class App extends Application {
    private static App instance;
    private static UserInfo sUserInfo;
    private static NetworkService mNetworkService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sUserInfo = UserInfo.getInstance();
        mNetworkService = NetworkService.getInstance();
    }

    public static App getInstance() {
        return instance;
    }

    public static UserInfo getUserInfo(){ return sUserInfo; }

    public static NetworkService getNetworkService(){
        return mNetworkService;
    }
}
