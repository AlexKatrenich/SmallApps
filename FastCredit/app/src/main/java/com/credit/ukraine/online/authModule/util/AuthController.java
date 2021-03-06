package com.credit.ukraine.online.authModule.util;

import com.credit.ukraine.online.App;
import com.credit.ukraine.online.creditModule.util.UserInfo;
import com.credit.ukraine.online.network.model.BaseResponse;
import com.credit.ukraine.online.network.model.userAuthRegModule.UserTokenResponse;
import com.credit.ukraine.online.network.model.userAuthRegModule.registerPhoneNumber.PhonePOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.registerPhoneNumber.PhoneRegisterResponse;
import com.credit.ukraine.online.network.model.userAuthRegModule.registerPhoneNumber.SecretCodePOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.setPassword.PasswordPOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.userAuth.PhonePassPOJO;

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

    public Single<BaseResponse> resendVerificationCode(String phone){

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

    public Single<BaseResponse> verificateCodeResetUser(String code) {
        SecretCodePOJO pojo = new SecretCodePOJO();
        pojo.setPhone(UserInfo.getInstance().getCurrentUser().getMobilePhone());
        pojo.setSecretCode(Integer.valueOf(code));

        return App.getNetworkService().getNetworkClient()
                .resetUserStatusBySecretCode(pojo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
