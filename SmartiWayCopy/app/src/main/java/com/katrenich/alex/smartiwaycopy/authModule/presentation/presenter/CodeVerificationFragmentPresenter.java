package com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.model.User;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.CodeVerificationView;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class CodeVerificationFragmentPresenter extends MvpPresenter<CodeVerificationView> {
    public MutableLiveData<String> messageTextViewData;
    private boolean dataLoad;

    public CodeVerificationFragmentPresenter() {
        init();
    }

    private void init() {
        messageTextViewData = new MutableLiveData<>();
        messageTextViewData.setValue(getMessageToUser());
        dataLoad = false;

        getViewState().updateUI();
    }

    private String getMessageToUser(){
        User user =  AuthController.getInstance().getUser();
        if (user == null) return " ";

        String userPhone = user.getMobilePhone();

        if(userPhone != null) {
            StringBuilder sb = new StringBuilder();
            String firstPartMessage = App.getInstance().getString(R.string.code_verification_fragment_message_to_user_1);
            sb.append(firstPartMessage);
            String phonePrefix = App.getInstance().getString(R.string.user_phone_number_prefix);
            sb.append(" " + phonePrefix);
            sb.append(userPhone + " ");
            String secondPartMessage = App.getInstance().getString(R.string.code_verification_fragment_message_to_user_2);
            sb.append(secondPartMessage);
            return sb.toString();
        }

        return " ";
    }

    public void verificationCodeEntered(String verificationCode) {
        int codeLength = App.getInstance().getResources().getInteger(R.integer.code_verification_length);
        if(verificationCode != null && verificationCode.length() == codeLength && !dataLoad){
            dataLoad = true;
            MainActivityNavigateController.getInstance().showProgress();
            AuthController.getInstance()
                    .checkUserVerificationCode(verificationCode)
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

    public void onChangePhoneNumberClicked(View view) {
        MainActivityNavigateController.getInstance().navigateBack();
    }
}
