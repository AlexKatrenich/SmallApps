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
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.UserPhoneView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.registerPhoneNumber.PhoneRegisterResponse;

import io.reactivex.disposables.Disposable;


@InjectViewState
public class UserPhoneFragmentPresenter extends MvpPresenter<UserPhoneView> {
    private static final String TAG = "UserPhoneFP";

    private UserInfo mUserInfo;
    private String authAction;
    public MutableLiveData<Integer> btnAuthVisible;
    public MutableLiveData<Integer> btnPolicyLicenceVisible;
    public MutableLiveData<Integer> tvLicenseVisible;
    public MutableLiveData<String> userMessage;
    private Disposable loadData;

    public UserPhoneFragmentPresenter() {
        init();
    }

    private void init() {
        mUserInfo = App.getUserInfo();

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
        if (phoneNumber.length() == 9 ){
            if (loadData != null && !loadData.isDisposed()) return;

            mUserInfo.getCurrentUser().setMobilePhone(phoneNumber);
            if (authAction.equals(App.getInstance().getString(R.string.auth_action_state))){
                sendPassRecoverCode(phoneNumber);
            } else {
                registerNewUser(phoneNumber);
            }
        }
    }


    private void sendPassRecoverCode(String phoneNumber) {
        Log.i(TAG, "sendPassRecoverCode: ");
        MainActivityNavigateController.getInstance().showProgress();

        loadData = AuthController.getInstance()
                .resendVerificationCode(phoneNumber)
                .subscribe(baseResponse -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    Bundle bundle = new Bundle();
                    bundle.putString(App.getInstance().getString(R.string.auth_state_action_name), authAction);
                    MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification, bundle);
                }, throwable -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                });
    }

    private void registerNewUser(String phoneNumber) {
        Log.i(TAG, "registerNewUser: ");
        MainActivityNavigateController.getInstance().showProgress();

        loadData = AuthController.getInstance()
                .sendVerificationCodeNewUser(phoneNumber)
                .map(PhoneRegisterResponse::getData)
                .subscribe(phoneRegisterPOJO -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    String s = phoneRegisterPOJO.getStatus();
                    Log.i(TAG, "registerNewUser: Status: " + s);
                    switch (s){
                        case "register" : {
                            getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text_phone_register_already));
                            break;
                        } case "verified" : {
                            MainActivityNavigateController.getInstance().showProgress();
                            loadData = AuthController.getInstance()
                                    .resendVerificationCode(phoneNumber)
                                    .subscribe(baseResponse -> {
                                        MainActivityNavigateController.getInstance().hideProgress();
                                        MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification);
                                    }, throwable -> {
                                        MainActivityNavigateController.getInstance().hideProgress();
                                        getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                                    });
                            break;
                        } case "new" : {
                            MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification);
                            break;
                        } default: {
                            break;
                        }
                    }
                }, throwable -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    Log.i(TAG, "registerNewUser: " + throwable.getMessage());
                    getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
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
        } else {
            MainActivityNavigateController.getInstance().hideBackButton();
        }
    }

    @Override
    public void onDestroy() {
        if (loadData != null) loadData.dispose();
        super.onDestroy();
    }
}
