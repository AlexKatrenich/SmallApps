package com.bistriycredit.online.nakartu.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.bistriycredit.online.nakartu.App;
import com.bistriycredit.online.nakartu.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final NetworkService mInstance = new NetworkService();
    private Retrofit mRetrofit;

    public static NetworkService getInstance() {
        return mInstance;
    }

    private NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Added login-interceptor and one else interceptor with static header, that will be added to every http-query
        OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-token", App.getInstance().getString(R.string.x_api_token))
                    .build();
            return chain.proceed(newRequest);
        });

        mRetrofit = new Retrofit.Builder()
                .baseUrl(App.getInstance().getString(R.string.test_server_api_address))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .build();
    }

    public ApiService getNetworkClient(){
        return mRetrofit.create(ApiService.class);
    }
}
