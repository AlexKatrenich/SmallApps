package com.katrenich.alex.smartiwaycopy.creditModule.util;

import android.util.Log;

import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.creditModule.model.Credit;
import com.katrenich.alex.smartiwaycopy.model.User;
import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;
import com.katrenich.alex.smartiwaycopy.network.model.credit.CreditPOJO;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.userData.AddUserDataResponse;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.userData.UserDataPOJO;

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
