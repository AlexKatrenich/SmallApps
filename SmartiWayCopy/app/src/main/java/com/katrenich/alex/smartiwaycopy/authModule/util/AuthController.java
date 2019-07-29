package com.katrenich.alex.smartiwaycopy.authModule.util;

import android.util.Log;

import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

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

    public Single<Boolean> checkUserPhone(String phoneNumber) {
        String number = App.getInstance().getString(R.string.user_mobile_phone_number);
        Boolean b = number.equals(phoneNumber);
        return Single.just(b);
    }

    public Single<Boolean> checkVerificationCodeNewUser(String code) {
        String baseCode = App.getInstance().getString(R.string.mobile_phone_verification_code);
        code = baseCode; //TEST
        return Single.just(baseCode.equals(code))
                .subscribeOn(Schedulers.io())
                .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Single<Boolean> sendVerificationCodeNewUser(String phoneNumber) {

        return Single.just(true)
               .subscribeOn(Schedulers.io())
               .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }

    public Single<Boolean> setUserPassword(String password) {

        return Single.just(true)
                .subscribeOn(Schedulers.io())
                .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }

    public Single<Boolean> authorizeUser(String userPhone, String password) {

        return Single.just(true)
                .subscribeOn(Schedulers.io())
                .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }

    public Single<Boolean> sendVerificationCodePassRecover(String phoneNumber) {

        // TEST
        return Single.just(true)
                .subscribeOn(Schedulers.io())
                .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }

    public Single<Boolean> checkVerificationCodePassRecover(String code) {

        String baseCode = App.getInstance().getString(R.string.mobile_phone_verification_code);
        code = baseCode; //TEST

        return Single.just(baseCode.equals(code))
                .subscribeOn(Schedulers.io())
                .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }

    public Single<Boolean> changeCurrentUserPassword(String password) {

        return Single.just(true)
                .subscribeOn(Schedulers.io())
                .delay(TEST_TIME_DELAY, TimeUnit.SECONDS);
    }
}
