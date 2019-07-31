package com.katrenich.alex.smartiwaycopy.network;

import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;
import com.katrenich.alex.smartiwaycopy.network.model.registerPhoneNumber.PhonePOJO;
import com.katrenich.alex.smartiwaycopy.network.model.registerPhoneNumber.PhoneRegisterResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("phone/register")
    Single<PhoneRegisterResponse> registerPhone(@Body PhonePOJO phone);

    @GET("phone/register")
    Single<BaseResponse> getNewCodeVerification(@Query("phone") String phone);


}
