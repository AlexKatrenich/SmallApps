package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.UserPhoneView;

@InjectViewState
public class UserPhoneFragmentPresenter extends MvpPresenter<UserPhoneView> {

    public void onPolicyButtonClicked(View view) {
        Context context = view.getContext();
        String webAddress = context.getString(R.string.info_dialog_fragment_website_address);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webAddress));
        context.startActivity(intent);
    }

    public void onButtonAuthClicked(View view) {

    }

}
