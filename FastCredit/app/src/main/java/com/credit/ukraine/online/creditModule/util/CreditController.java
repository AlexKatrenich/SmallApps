package com.credit.ukraine.online.creditModule.util;

import android.util.Log;

import com.credit.ukraine.online.App;
import com.credit.ukraine.online.creditModule.model.Credit;
import com.credit.ukraine.online.model.User;
import com.credit.ukraine.online.network.model.BaseResponse;
import com.credit.ukraine.online.network.model.credit.CreditPOJO;
import com.credit.ukraine.online.network.model.userAuthRegModule.userData.AddUserDataResponse;
import com.credit.ukraine.online.network.model.userAuthRegModule.userData.UserDataPOJO;

import java.text.ParseException;
import java.util.zip.DataFormatException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CreditController {
    private static final CreditController ourInstance = new CreditController();
    private static final String TAG = "CreditController";
    private Credit mCredit;

    public static CreditController getInstance() {
        return ourInstance;
    }

    private CreditController() {
        mCredit = new Credit();
    }

    public Single<Response<AddUserDataResponse>> setUserDataToServer(User user, String token) throws DataFormatException {
        try {
            UserDataPOJO pojo = UserDataPOJO.getPOJOfromUserEntity(user, token);
            return App.getNetworkService().getNetworkClient()
                    .setUserData(pojo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } catch (ParseException e) {
            Log.e(TAG, "setUserDataToServer: " + e.getMessage());
            throw new DataFormatException("Can`t create UserInfoPojo from user");
        }
    }

    public Single<Response<BaseResponse>> sendCreditQuery(String userToken) {
        CreditPOJO pojo = new CreditPOJO();
        pojo.setCreditReturnDate(mCredit.getReturnDate());
        pojo.setCreditValue(mCredit.getLoanAmount());
        pojo.setRememberToken(userToken);

        Log.i(TAG, "sendCreditQuery: POJO= " + pojo);

        return App.getNetworkService().getNetworkClient()
                .getCredit(pojo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Credit getCredit() {
        return mCredit;
    }
}
