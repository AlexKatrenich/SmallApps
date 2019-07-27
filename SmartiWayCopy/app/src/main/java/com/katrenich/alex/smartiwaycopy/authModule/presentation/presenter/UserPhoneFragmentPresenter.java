package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

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

    private String authAction;
    public MutableLiveData<Integer> btnAuthVisible;
    public MutableLiveData<Integer> btnPolicyLicenceVisible;
    public MutableLiveData<Integer> tvLicenseVisible;
    public MutableLiveData<String> userMessage;

    public UserPhoneFragmentPresenter() {
        init();
    }

    private void init() {
        btnAuthVisible = new MutableLiveData<>();
        btnPolicyLicenceVisible = new MutableLiveData<>();
        tvLicenseVisible = new MutableLiveData<>();
        userMessage = new MutableLiveData<>();

        btnAuthVisible.setValue(View.VISIBLE);
        btnPolicyLicenceVisible.setValue(View.VISIBLE);
        tvLicenseVisible.setValue(View.VISIBLE);
        userMessage.setValue(App.getInstance().getString(R.string.user_phone_fragment_tv_hint_caption));

        getViewState().updateUI();
    }

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
            if (authAction.equals(App.getInstance().getString(R.string.auth_action_state))){
                sendPassRecoverCode(phoneNumber);
            } else {
                sendCodeNewUser(phoneNumber);
            }
        }
    }

    private void sendPassRecoverCode(String phoneNumber) {
        MainActivityNavigateController.getInstance().showProgress();
        AuthController.getInstance()
                .sendVerificationCodePassRecover(new User(phoneNumber))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    if (aBoolean) {
                        Bundle bundle = new Bundle();
                        bundle.putString(App.getInstance().getString(R.string.auth_state_action_name), authAction);
                        MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification, bundle);
                    } else {
                        getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                    }
                });
    }


    private void sendCodeNewUser(String phoneNumber) {
        MainActivityNavigateController.getInstance().showProgress();
        AuthController.getInstance()
                .sendVerificationCodeNewUser(new User(phoneNumber))
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

    public void setAuthAction(String authAction) {
        this.authAction = authAction;

        if(authAction != null && authAction.equals(App.getInstance().getString(R.string.auth_action_state))){
            btnAuthVisible.setValue(View.GONE);
            btnPolicyLicenceVisible.setValue(View.GONE);
            tvLicenseVisible.setValue(View.GONE);
            userMessage.setValue(App.getInstance().getString(R.string.user_phone_fragment_tv_hint_caption_to_pass_recover));
            MainActivityNavigateController.getInstance().showBackButton();
        }
    }
}
