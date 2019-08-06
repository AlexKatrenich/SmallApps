package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.PasswordSettingView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;
import com.katrenich.alex.smartiwaycopy.utils.ApiKeyPrefUtils;

import java.util.regex.Pattern;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class PasswordSettingFragmentPresenter extends MvpPresenter<PasswordSettingView> {

    private static final String TAG = "PasswordSettingFragPres";
    private String authAction;
    private Disposable loadData;
    private UserInfo mUserInfo;

    public PasswordSettingFragmentPresenter() {
        mUserInfo = App.getUserInfo();
    }

    public void onButtonConfirmClicked(String password, String passConfirm) {
        if(!password.equals(passConfirm)) return;
        if(loadData != null && !loadData.isDisposed()) return;

        setUserPassword(password);
    }

    private void setUserPassword(String password){
        MainActivityNavigateController.getInstance().showProgress();
        loadData = AuthController.getInstance()
                .setUserPassword(password)
                .map(passwordSaveResponse -> passwordSaveResponse.getData())
                .subscribe(userTokenPOJO -> {
                            MainActivityNavigateController.getInstance().hideProgress();
                            Log.i(TAG, "setUserPassword: passwordResponsePOJO = " + userTokenPOJO);
                            String token = userTokenPOJO.getToken();
                            if (token != null) {
                                ApiKeyPrefUtils.storeApiKey(App.getInstance(), token);
                                Log.i(TAG, "setUserPassword: TOKEN = " + token);
                                writeUserPhoneToSharedPref();
                                MainActivityNavigateController.getInstance().navigate(R.id.action_passwordSetting_to_credit);
                            } else {
                                Log.i(TAG, "setUserPassword: TOKEN == NULL");
                            }
                        }, throwable -> {
                            MainActivityNavigateController.getInstance().hideProgress();
                            getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                        }
                );
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

    @Override
    public void onDestroy() {
        if(loadData != null) loadData.dispose();
        super.onDestroy();
    }
}
