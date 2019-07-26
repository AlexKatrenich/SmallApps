package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.model.User;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.UserPhoneView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import java.util.Calendar;
import java.util.Date;

@InjectViewState
public class UserPhoneFragmentPresenter extends MvpPresenter<UserPhoneView> {

    private static final String TAG = "UserPhoneFP";

    public void onPolicyButtonClicked(View view) {
        Context context = view.getContext();
        String webAddress = context.getString(R.string.info_dialog_fragment_website_address);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webAddress));
        context.startActivity(intent);
    }

    public void onButtonAuthClicked(View view) {
        Log.i(TAG, "onButtonAuthClicked: ");
        MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_authorization);
    }

    private Date time;

    public void phoneNumberEntered(String phoneNumber) {
        if(time != null) {
            Date currentTime = Calendar.getInstance().getTime();
            long t = currentTime.getTime() - time.getTime();
            if(t < 500) {return;}
        } else {
            time = Calendar.getInstance().getTime();
        }
        AuthController.getInstance().setUser(new User(phoneNumber));
        AuthController.getInstance().sendVerificationCode();
        MainActivityNavigateController.getInstance().navigate(R.id.action_userPhone_to_codeVerification);
    }
}
