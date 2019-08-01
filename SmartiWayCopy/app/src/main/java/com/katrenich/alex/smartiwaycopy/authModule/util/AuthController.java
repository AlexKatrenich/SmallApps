package com.katrenich.alex.smartiwaycopy.authModule.util;

import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.registerPhoneNumber.PhonePOJO;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.registerPhoneNumber.PhoneRegisterResponse;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.registerPhoneNumber.SecretCodePOJO;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.setPassword.PasswordPOJO;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.UserTokenResponse;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.userAuth.PhonePassPOJO;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AuthController {
    public static final String TAG = "AuthController";
    private static final AuthController ourInstance = new AuthController();
    private String token;

    public static AuthController getInstance() {
        return ourInstance;
    }
    private final int TEST_TIME_DELAY = 0;

    private AuthController() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Single<BaseResponse> resendVerificationCodeNewUser(String phone){

        return App.getNetworkService().getNetworkClient()
                .getNewSecretCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<PhoneRegisterResponse> sendVerificationCodeNewUser(String phoneNumber) {
        PhonePOJO phone = new PhonePOJO();
        phone.setPhone(phoneNumber);

        return App.getNetworkService().getNetworkClient()
                .getSecretCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<UserTokenResponse> setUserPassword(String password) {
        PasswordPOJO pojo = new PasswordPOJO();
        pojo.setPassword(password);
        pojo.setPhone(UserInfo.getInstance().getCurrentUser().getMobilePhone());

        return App.getNetworkService().getNetworkClient()
                .savePassword(pojo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Response<UserTokenResponse>> authorizeUser(String userPhone, String password) {
        PhonePassPOJO pojo = new PhonePassPOJO();
        pojo.setPassword(password);
        pojo.setPhone(userPhone);

        // TODO auth
        return App.getNetworkService().getNetworkClient()
                .authUser(pojo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> sendVerificationCodePassRecover(String phoneNumber) {

        // TEST
        return Single.just(true)
                .subscribeOn(Schedulers.io())
                .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }

    public Single<BaseResponse> verificateCode(String code) {
        SecretCodePOJO pojo = new SecretCodePOJO();
        pojo.setPhone(UserInfo.getInstance().getCurrentUser().getMobilePhone());
        pojo.setSecretCode(Integer.valueOf(code));

        return App.getNetworkService().getNetworkClient()
                .verificateSecretCode(pojo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> changeCurrentUserPassword(String password) {

        return Single.just(true)
                .subscribeOn(Schedulers.io())
                .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }
}
