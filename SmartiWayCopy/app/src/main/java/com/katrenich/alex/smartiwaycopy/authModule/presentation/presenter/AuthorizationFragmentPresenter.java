package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.AuthorizationView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;
import com.katrenich.alex.smartiwaycopy.model.User;
import com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.UserTokenPOJO;
import com.katrenich.alex.smartiwaycopy.utils.ApiKeyPrefUtils;

import java.util.regex.Pattern;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class AuthorizationFragmentPresenter extends MvpPresenter<AuthorizationView> {

    private static final String TAG = "AuthorizationFragmentP";
    private UserInfo mUserInfo;
    private Disposable loadData;
    public MutableLiveData<String> userPhoneNumber;

    public AuthorizationFragmentPresenter() {
        init();
        getViewState().updateUI();
    }

    private void init() {
        mUserInfo = App.getUserInfo();
        userPhoneNumber = new MutableLiveData<>();
        getUserPhoneFromSharedPref();
    }

    private void getUserPhoneFromSharedPref() {
        Context context = App.getInstance();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        String userPhoneNumb = sharedPref.getString(context.getString(R.string.shared_preference_phone_numb), " ");
        userPhoneNumber.setValue(userPhoneNumb);
    }

    public void onButtonAuthClicked(String phoneNumb, String pass) {
        if(phoneNumb != null && pass != null){
            if(phoneNumb.length() != 9) {
                getViewState().showMessage(App.getInstance().getString(R.string.authorization_fragment_message_pass_and_phone_not_confirm));
                return;
            }
            if(!verifyPass(pass)) {
                getViewState().showMessage(App.getInstance().getString(R.string.authorization_fragment_message_pass_and_phone_not_confirm));
                return;
            }

            if (loadData != null && !loadData.isDisposed()) return;
            MainActivityNavigateController.getInstance().showProgress();

            loadData = AuthController.getInstance()
                    .authorizeUser(phoneNumb, pass)
                    .subscribe(userTokenResponseResponse -> {
                        MainActivityNavigateController.getInstance().hideProgress();
                       if (userTokenResponseResponse.isSuccessful()){
                           Log.i(TAG, "onButtonAuthClicked: userTokenResponseResponse.isSuccessful");
                           UserTokenPOJO data = userTokenResponseResponse.body().getData();
                           if (data != null){
                            String token = data.getToken();
                            mUserInfo.getCurrentUser().setMobilePhone(phoneNumb);
                            ApiKeyPrefUtils.storeApiKey(App.getInstance(), token);
                            writePhoneToSharPref(phoneNumb);
                            MainActivityNavigateController.getInstance().navigate(R.id.action_authorization_to_credit);
                           } else {
                               Log.e(TAG, "onButtonAuthClicked: " + userTokenResponseResponse.body());
                           }
                       } else {
                           int code = userTokenResponseResponse.code();
                           if(code == 406 || code == 422) {
                               getViewState().showMessage(App.getInstance().getString(R.string.authorization_fragment_message_pass_and_phone_not_confirm));
                           } else  {
                               Log.i(TAG, "onButtonAuthClicked:" + userTokenResponseResponse.message());
                               getViewState().showMessage(App.getInstance().getString(R.string.authorization_fragment_message_server_not_respond));
                           }
                       }
                    });
        } else {
            getViewState().showMessage(App.getInstance().getString(R.string.authorization_fragment_message_pass_and_phone_not_confirm));
        }
    }

    @Override
    public void onDestroy() {
        if(loadData != null) loadData.dispose();
        super.onDestroy();
    }

    public boolean verifyPass(String s) {
        String PASS_PATTERN = App.getInstance().getString(R.string.pass_verification_pattern_regex);
        return Pattern.compile(PASS_PATTERN).matcher(s).matches();
    }

    private void writePhoneToSharPref(String phoneNumb) {
        if(phoneNumb != null){
            Context context = App.getInstance();
            SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(context.getString(R.string.shared_preference_phone_numb), phoneNumb);
            editor.apply();
        }
    }

    public void onButtonPassRecoverClicked(View view) {
        if(App.getUserInfo().getCurrentUser() != null){
            User user = mUserInfo.getCurrentUser();
            user.setMobilePhone("");
            mUserInfo.setCurrentUser(user);
        }
        MainActivityNavigateController.getInstance().navigate(R.id.action_authorizationFragment_to_userPhoneFragment);
    }
}
