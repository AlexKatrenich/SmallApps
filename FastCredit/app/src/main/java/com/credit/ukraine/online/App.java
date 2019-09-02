package com.credit.ukraine.online;

import android.app.Application;

import com.credit.ukraine.online.creditModule.util.UserInfo;
import com.credit.ukraine.online.network.NetworkService;
import com.onesignal.OneSignal;

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

        // Init onesignal service
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init();
    }

    public static App getInstance() {
        return instance;
    }

    public static UserInfo getUserInfo(){ return sUserInfo; }

    public static NetworkService getNetworkService(){
        return mNetworkService;
    }
}
