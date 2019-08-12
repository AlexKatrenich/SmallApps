package com.bistriycredit.online.nakartu;

import android.app.Application;

import com.bistriycredit.online.nakartu.creditModule.util.UserInfo;
import com.bistriycredit.online.nakartu.network.NetworkService;

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
