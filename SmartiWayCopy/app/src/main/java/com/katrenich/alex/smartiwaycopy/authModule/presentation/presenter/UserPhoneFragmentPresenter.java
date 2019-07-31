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
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.model.User;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.UserPhoneView;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;


@InjectViewState
public class UserPhoneFragmentPresenter extends MvpPresenter<UserPhoneView> {
    private static final String TAG = "UserPhoneFP";

    private UserInfo mUserInfo;
    private String authAction;
    public MutableLiveData<Integer> btnAuthVisible;
    public MutableLiveData<Integer> btnPolicyLicenceVisible;
    public MutableLiveData<Integer> tvLicenseVisible;
    public MutableLiveData<String> userMessage;
    private boolean dataLoading;

    public UserPhoneFragmentPresenter() {
        init();
    }

    private void init() {
        mUserInfo = App.getUserInfo();
        dataLoading = false;

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
        if (phoneNumber.length() == 9 && !dataLoading){
            dataLoading = true;
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

        // TEST
        dataLoading = false;
        Bundle bundle = new Bundle();
        bundle.putString(App.getInstance().getString(R.string.auth_state_action_name), authAction);
        MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification, bundle);
    }

    private void registerNewUser(String phoneNumber) {
        Log.i(TAG, "registerNewUser: ");

        dataLoading = false;
        MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification);

//        AuthController.getInstance()
//                .sendVerificationCodeNewUser(phoneNumber)
//                .subscribe(phoneRegisterResponse -> {
//                    MainActivityNavigateController.getInstance().hideProgress();
//                    dataLoading = false;
//
//                    if(phoneRegisterResponse.getSuccess()){
//                        mUserInfo.getCurrentUser().setMobilePhone(phoneNumber);
//                        MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification);
//                    } else {
//                        getViewState().showMessage("Такий номер телефону вже зареєстровано");
//                    }
//                        }
//                , throwable -> {
//                       dataLoading = false;
//                       MainActivityNavigateController.getInstance().hideProgress();
//                       getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
//                        });
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
}
