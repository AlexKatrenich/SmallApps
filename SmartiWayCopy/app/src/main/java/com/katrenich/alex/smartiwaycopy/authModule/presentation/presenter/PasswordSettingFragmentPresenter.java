package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.PasswordSettingView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class PasswordSettingFragmentPresenter extends MvpPresenter<PasswordSettingView> {

    public PasswordSettingFragmentPresenter() {

    }

    public void onButtonConfirmClicked(String password, String passConfirm) {
        if(!password.equals(passConfirm)) return;

        MainActivityNavigateController.getInstance().showProgress();
        AuthController.getInstance()
                .setUserPassword(password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    if(aBoolean) {
                        MainActivityNavigateController.getInstance().navigate(R.id.action_passwordSetting_to_credit);
                    } else {
                        getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                    }
                });
    }

    public boolean verifyPass(String s) {
        String PASS_PATTERN = App.getInstance().getString(R.string.pass_verification_pattern_regex);
        return Pattern.compile(PASS_PATTERN).matcher(s).matches();
    }

}
