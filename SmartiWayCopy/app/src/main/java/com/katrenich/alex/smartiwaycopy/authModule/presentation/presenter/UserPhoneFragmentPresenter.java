package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.model.User;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.UserPhoneView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import io.reactivex.android.schedulers.AndroidSchedulers;


@InjectViewState
public class UserPhoneFragmentPresenter extends MvpPresenter<UserPhoneView> {

    private static final String TAG = "UserPhoneFP";

    public void onPolicyButtonClicked(View view) {
        Context context = view.getContext();
        String webAddress = context.getString(R.string.main_company_website_address);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webAddress));
        context.startActivity(intent);
    }

    public void onButtonAuthClicked(View view) {
        Log.i(TAG, "onButtonAuthClicked: ");
        MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_authorization);
    }


    public void phoneNumberEntered(String phoneNumber) {
        // check if calling method with same parameter - return;
        User user = AuthController.getInstance().getUser();
        if (user != null && user.getMobilePhone() != null) {
            if(user.getMobilePhone().equals(phoneNumber)) return;
        }

        if (phoneNumber.length() == 10){
            AuthController.getInstance().setUser(new User(phoneNumber));
            MainActivityNavigateController.getInstance().showProgress();
            AuthController.getInstance()
                    .sendVerificationCode()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        MainActivityNavigateController.getInstance().hideProgress();
                        if (aBoolean) {
                            MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification);
                        } else {
                            getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                        }
                    });

        }
    }
}
