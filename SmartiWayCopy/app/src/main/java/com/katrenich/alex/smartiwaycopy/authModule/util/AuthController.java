package com.katrenich.alex.smartiwaycopy.authModule.util;

import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;
import com.katrenich.alex.smartiwaycopy.network.model.checkPhoneNumber.PhoneCheckResponse;
import com.katrenich.alex.smartiwaycopy.network.model.checkPhoneNumber.PhoneExistsPOJO;
import com.katrenich.alex.smartiwaycopy.network.model.registerPhoneNumber.PhonePOJO;
import com.katrenich.alex.smartiwaycopy.network.model.registerPhoneNumber.PhoneRegisterPOJO;
import com.katrenich.alex.smartiwaycopy.network.model.registerPhoneNumber.PhoneRegisterResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    public Single<BaseResponse> resendVerificationCodeNewUser(String phone){

        return App.getNetworkService().getNetworkClient()
                .getNewCodeVerification(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<PhoneRegisterResponse> sendVerificationCodeNewUser(String phoneNumber) {
        PhonePOJO phone = new PhonePOJO();
        phone.setPhone(phoneNumber);

        return App.getNetworkService().getNetworkClient()
                .registerPhone(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
