package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.model.User;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.CodeVerificationView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CodeVerificationFragmentPresenter extends MvpPresenter<CodeVerificationView> {
    private static final String TAG = "CodeVerFragmentP";
    public MutableLiveData<String> messageTextViewData;
    public MutableLiveData<Integer> btnResendCodeVisibility;
    private boolean dataLoad;
    private String action;
    private UserInfo mUserInfo;


    public CodeVerificationFragmentPresenter() {
        init();
    }

    private void init() {
        mUserInfo = App.getUserInfo();
        messageTextViewData = new MutableLiveData<>();
        messageTextViewData.setValue(getMessageToUser());
        btnResendCodeVisibility = new MutableLiveData<>();
        dataLoad = false;

        initTimeToShowResendButton(10);
        getViewState().updateUI();
    }


    private void initTimeToShowResendButton(int sec) {
        Single.just(View.VISIBLE)
                .subscribeOn(Schedulers.io())
                .delay(sec, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(btnResendCodeVisibility::setValue);
    }


    private String getMessageToUser(){
        Log.i(TAG, "getMessageToUser: ");
        User user = mUserInfo.getCurrentUser();
        Log.i(TAG, "getMessageToUser: " + user) ;
        String userPhone = user.getMobilePhone();

        if(userPhone != null) {
            StringBuilder sb = new StringBuilder();
            String firstPartMessage = App.getInstance().getString(R.string.code_verification_fragment_message_to_user_1);
            sb.append(firstPartMessage);
            String phonePrefix = App.getInstance().getString(R.string.user_phone_number_prefix_text);
            sb.append(" " + phonePrefix);
            sb.append(userPhone + " ");
            String secondPartMessage = App.getInstance().getString(R.string.code_verification_fragment_message_to_user_2);
            sb.append(secondPartMessage);
            return sb.toString();
        }

        return " ";
    }

    public void verificationCodeEntered(String verificationCode) {
        if(action != null && action.equals(App.getInstance().getString(R.string.auth_action_state))){
            verificateCodePassRecover(verificationCode);
        } else {
            verificateCodeNewUser(verificationCode);
        }
    }

    private void verificateCodeNewUser(String verificationCode) {
        int codeLength = App.getInstance().getResources().getInteger(R.integer.code_verification_length);
        if(verificationCode != null && verificationCode.length() == codeLength && !dataLoad){
            dataLoad = true;
            MainActivityNavigateController.getInstance().showProgress();
            AuthController.getInstance()
                    .checkVerificationCodeNewUser(verificationCode)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        MainActivityNavigateController.getInstance().hideProgress();
                        dataLoad = false;
                        if (aBoolean) {
                            MainActivityNavigateController.getInstance().navigate(R.id.action_codeVerification_to_passwordSetting);
                        } else {
                            getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                        }
                    });
        }
    }

    private void verificateCodePassRecover(String verificationCode) {
        Log.i(TAG, "verificateCodePassRecover: ");
        int codeLength = App.getInstance().getResources().getInteger(R.integer.code_verification_length);
        if(verificationCode != null && verificationCode.length() == codeLength && !dataLoad){
            dataLoad = true;
            MainActivityNavigateController.getInstance().showProgress();
            AuthController.getInstance()
                    .checkVerificationCodePassRecover(verificationCode)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        MainActivityNavigateController.getInstance().hideProgress();
                        dataLoad = false;
                        if (aBoolean) {
                            Bundle bundle = new Bundle();
                            bundle.putString(App.getInstance().getString(R.string.auth_state_action_name), action);
                            MainActivityNavigateController.getInstance().navigate(R.id.action_codeVerification_to_passwordSetting, bundle);
                        } else {
                            getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                        }
                    });
        }
    }

    public void onChangePhoneNumberClicked(View view) {
        MainActivityNavigateController.getInstance().navigateBack();
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void onButtonResendCodeClicked(View view) {

    }
}
