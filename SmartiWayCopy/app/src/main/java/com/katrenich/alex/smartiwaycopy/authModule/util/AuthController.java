package com.katrenich.alex.smartiwaycopy.authModule.util;

import android.util.Log;

import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.model.User;

import java.util.concurrent.TimeUnit;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class AuthController {
    public static final String TAG = "AuthController";
    private static final AuthController ourInstance = new AuthController();
    private User mUser;
    private String token;

    public static AuthController getInstance() {
        return ourInstance;
    }

    private AuthController() {

    }

    public Single<Boolean> checkUserPhone(String phoneNumber) {
        String number = App.getInstance().getString(R.string.user_mobile_phone_number);
        Boolean b = number.equals(phoneNumber);
        return Single.just(b);
    }

    public Single<Boolean> checkUserVerificationCode(String code) {
        String baseCode = App.getInstance().getString(R.string.mobile_phone_verification_code);

        return Single.just(baseCode.equals(code))
                .subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS);
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Single<Boolean> sendVerificationCode() {
        boolean b = false;
        if (mUser != null && mUser.getMobilePhone() != null){
            Log.i(TAG, "sendVerificationCode: " + mUser.getMobilePhone());
            b = mUser.getMobilePhone().equals(App.getInstance().getString(R.string.user_mobile_phone_number));
        }

        return Single.just(b)
               .subscribeOn(Schedulers.io())
               .delay(2, TimeUnit.SECONDS);
    }
}
