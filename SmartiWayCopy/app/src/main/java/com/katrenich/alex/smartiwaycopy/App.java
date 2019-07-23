package com.katrenich.alex.smartiwaycopy;

import android.app.Application;

import com.katrenich.alex.smartiwaycopy.di.AppComponent;
import com.katrenich.alex.smartiwaycopy.di.DaggerAppComponent;
import com.katrenich.alex.smartiwaycopy.utils.AppModule;

public class App extends Application {
    private static AppComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(){
        return sComponent;
    }

}
