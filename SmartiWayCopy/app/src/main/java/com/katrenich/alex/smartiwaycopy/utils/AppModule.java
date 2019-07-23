package com.katrenich.alex.smartiwaycopy.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context mContext;

    public AppModule(@NonNull Context context){
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }
}
