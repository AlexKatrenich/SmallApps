package com.katrenich.alex.smartiwaycopy.network;

import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.registerPhoneNumber.PhonePOJO;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.registerPhoneNumber.PhoneRegisterResponse;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.registerPhoneNumber.SecretCodePOJO;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.setPassword.PasswordPOJO;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.UserTokenResponse;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.userAuth.PhonePassPOJO;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("phone/register")
    Single<PhoneRegisterResponse> getSecretCode(@Body PhonePOJO phone);

    @GET("phone/register")
    Single<BaseResponse> getNewSecretCode(@Query("phone") String phone);

    @POST("phone/verification")
    Single<BaseResponse> verificateSecretCode(@Body SecretCodePOJO code);

    @POST("phone/save-password")
    Single<UserTokenResponse> savePassword(@Body PasswordPOJO password);

    @POST("phone/login")
    Single<Response<UserTokenResponse>> authUser(@Body PhonePassPOJO auth);
}
