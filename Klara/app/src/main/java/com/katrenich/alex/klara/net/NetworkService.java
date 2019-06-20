package com.katrenich.alex.klara.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;

public class NetworkService {
    private static NetworkService mInstance;
    public static final String BASE_URL = "https://klara.ua/";
    private Retrofit mRetrofit;

    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(KlaraPageConverterFactory.FACTORY)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        return mInstance == null ? new NetworkService() : mInstance;
    }

    public KlaraWebSiteQueries getKlaraWebSiteInfo(){
        return mRetrofit.create(KlaraWebSiteQueries.class);
    }
}
