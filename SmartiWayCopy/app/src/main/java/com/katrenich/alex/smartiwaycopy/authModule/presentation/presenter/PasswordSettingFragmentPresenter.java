package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.content.Context;
import android.content.SharedPreferences;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.PasswordSettingView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class PasswordSettingFragmentPresenter extends MvpPresenter<PasswordSettingView> {

    private String authAction;
    private boolean loadingData;
    private UserInfo mUserInfo;

    public PasswordSettingFragmentPresenter() {
        loadingData = false;
        mUserInfo = App.getUserInfo();
    }

    public void onButtonConfirmClicked(String password, String passConfirm) {
        if(!password.equals(passConfirm)) return;
        if(loadingData) return;

        if (authAction != null && authAction.equals(App.getInstance().getString(R.string.auth_action_state))){
            changeCurrentUserPassword(password);
        } else {
            setNewUserPassword(password);
        }
    }

    private void changeCurrentUserPassword(String password) {
        MainActivityNavigateController.getInstance().showProgress();
        loadingData = true;
        AuthController.getInstance()
                .changeCurrentUserPassword(password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    loadingData = false;
                    MainActivityNavigateController.getInstance().hideProgress();
                    if(aBoolean) {
                        writeUserPhoneToSharedPref();
                        MainActivityNavigateController.getInstance().navigate(R.id.action_passwordSetting_to_credit);
                    } else {
                        getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                    }
                });
    }


    private void setNewUserPassword(String password){
        MainActivityNavigateController.getInstance().showProgress();
        loadingData = true;
        AuthController.getInstance()
                .setUserPassword(password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    loadingData = false;
                    MainActivityNavigateController.getInstance().hideProgress();
                    if(aBoolean) {
                        writeUserPhoneToSharedPref();
                        MainActivityNavigateController.getInstance().navigate(R.id.action_passwordSetting_to_credit);
                    } else {
                        getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                    }
                });
    }

    private void writeUserPhoneToSharedPref() {
        String phoneNumber = mUserInfo.getCurrentUser().getMobilePhone();
        if(phoneNumber != null){
            Context context = App.getInstance();
            SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(context.getString(R.string.shared_preference_phone_numb), phoneNumber);
            editor.apply();
        }
    }

    public boolean verifyPass(String s) {
        String PASS_PATTERN = App.getInstance().getString(R.string.pass_verification_pattern_regex);
        return Pattern.compile(PASS_PATTERN).matcher(s).matches();
    }

    public void setAuthAction(String authAction) {
        this.authAction = authAction;
    }
}
