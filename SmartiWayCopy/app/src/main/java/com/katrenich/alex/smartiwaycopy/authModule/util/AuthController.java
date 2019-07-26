package com.katrenich.alex.smartiwaycopy.authModule.util;

import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.model.User;

import io.reactivex.Single;

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
        String baseCode = App.getInstance().getString(R.string.mobile_phone_varification_code);
        return Single.just(baseCode.equals(code));
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

    public void sendVerificationCode() {

    }
}
