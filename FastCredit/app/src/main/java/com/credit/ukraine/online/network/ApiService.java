package com.credit.ukraine.online.network;

import com.credit.ukraine.online.network.model.BaseResponse;
import com.credit.ukraine.online.network.model.credit.CreditPOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.UserTokenResponse;
import com.credit.ukraine.online.network.model.userAuthRegModule.registerPhoneNumber.PhonePOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.registerPhoneNumber.PhoneRegisterResponse;
import com.credit.ukraine.online.network.model.userAuthRegModule.registerPhoneNumber.SecretCodePOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.setPassword.PasswordPOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.userAuth.PhonePassPOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.userData.AddUserDataResponse;
import com.credit.ukraine.online.network.model.userAuthRegModule.userData.UserDataPOJO;

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

    @POST("phone/reset")
    Single<BaseResponse> resetUserStatusBySecretCode(@Body SecretCodePOJO code);

    @POST("phone/save-password")
    Single<UserTokenResponse> savePassword(@Body PasswordPOJO password);

    @POST("phone/login")
    Single<Response<UserTokenResponse>> authUser(@Body PhonePassPOJO auth);

    @POST("user")
    Single<Response<AddUserDataResponse>> setUserData(@Body UserDataPOJO dataPOJO);

    @POST("credit")
    Single<Response<BaseResponse>> getCredit(@Body CreditPOJO credit);
}
