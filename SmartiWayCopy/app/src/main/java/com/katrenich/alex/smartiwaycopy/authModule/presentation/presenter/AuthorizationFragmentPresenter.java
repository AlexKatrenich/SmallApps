package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.model.User;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.AuthorizationView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class AuthorizationFragmentPresenter extends MvpPresenter<AuthorizationView> {

    private UserInfo mUserInfo;

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
            MainActivityNavigateController.getInstance().showProgress();
            AuthController.getInstance()
                    .authorizeUser(phoneNumb, pass)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        MainActivityNavigateController.getInstance().hideProgress();
                        if(aBoolean) {
                            User currentUser = mUserInfo.getCurrentUser();
                            currentUser.setMobilePhone(phoneNumb);
                            mUserInfo.setCurrentUser(currentUser);
                            MainActivityNavigateController.getInstance().navigate(R.id.action_authorization_to_credit);
                        } else {
                            getViewState().showMessage(App.getInstance().getString(R.string.authorization_fragment_message_pass_and_phone_not_confirm));
                        }
                    });
        } else {
            getViewState().showMessage(App.getInstance().getString(R.string.authorization_fragment_message_pass_and_phone_not_confirm));
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
