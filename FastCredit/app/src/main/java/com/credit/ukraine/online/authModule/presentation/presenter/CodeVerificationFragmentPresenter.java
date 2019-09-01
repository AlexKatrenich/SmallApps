package com.credit.ukraine.online.authModule.presentation.presenter;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.credit.ukraine.online.App;
import com.credit.ukraine.online.R;
import com.credit.ukraine.online.authModule.presentation.view.CodeVerificationView;
import com.credit.ukraine.online.authModule.util.AuthController;
import com.credit.ukraine.online.creditModule.util.UserInfo;
import com.credit.ukraine.online.mainModule.util.MainActivityNavigateController;
import com.credit.ukraine.online.model.User;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CodeVerificationFragmentPresenter extends MvpPresenter<CodeVerificationView> {
    private static final String TAG = "CodeVerFragmentP";
    public MutableLiveData<String> messageTextViewData;
    public MutableLiveData<Integer> btnResendCodeVisibility;
    private String action;
    private int sec = 15; // seconds to show button Resend Secret Code
    private UserInfo mUserInfo;
    private Disposable loadData;

    public CodeVerificationFragmentPresenter() {
        init();
    }

    private void init() {
        mUserInfo = App.getUserInfo();
        messageTextViewData = new MutableLiveData<>();
        messageTextViewData.setValue(getMessageToUser());
        btnResendCodeVisibility = new MutableLiveData<>();

        showResendButtonAfter(sec);
        getViewState().updateUI();


    }


    private void showResendButtonAfter(int sec) {
        btnResendCodeVisibility.setValue(View.GONE);
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
        int codeLength = App.getInstance().getResources().getInteger(R.integer.code_verification_length);
        if(verificationCode != null && verificationCode.length() == codeLength) {
            if(loadData != null && !loadData.isDisposed()) return;
            MainActivityNavigateController.getInstance().showProgress();

            if(action != null && action.equals(App.getInstance().getString(R.string.auth_action_state))){
                verificateCodeOldUser(verificationCode);
            } else {
                verificateCodeNewUser(verificationCode);
            }

        }
    }

    private void verificateCodeOldUser(String code) {
        Log.i(TAG, "verificateCodeOldUser: ");
        loadData = AuthController.getInstance()
                .verificateCodeResetUser(code)
                .subscribe(baseResponse -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    MainActivityNavigateController.getInstance().navigate(R.id.action_codeVerification_to_passwordSetting);
                }, throwable -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    getViewState().showMessage(App.getInstance().getString(R.string.code_verification_fragment_code_error_text));
                });

    }

    private void verificateCodeNewUser(String code) {
        Log.i(TAG, "verificateCodeNewUser: ");
        loadData = AuthController.getInstance()
                .verificateCode(code)
                .subscribe(baseResponse -> {

                    MainActivityNavigateController.getInstance().hideProgress();
                    MainActivityNavigateController.getInstance().navigate(R.id.action_codeVerification_to_passwordSetting);
                }, throwable -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    getViewState().showMessage(App.getInstance().getString(R.string.code_verification_fragment_code_error_text));
                });
    }

    public void onChangePhoneNumberClicked(View view) {
        MainActivityNavigateController.getInstance().navigateBack();
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void onButtonResendCodeClicked(View view) {
        String phone = UserInfo.getInstance().getCurrentUser().getMobilePhone();
        if(phone != null){
            if(loadData != null && !loadData.isDisposed()) return;

            MainActivityNavigateController.getInstance().showProgress();
            showResendButtonAfter(sec);
            loadData = AuthController.getInstance()
                    .resendVerificationCode(phone)
                    .subscribe(baseResponse -> {
                        MainActivityNavigateController.getInstance().hideProgress();
                    }, throwable -> {
                        MainActivityNavigateController.getInstance().hideProgress();
                        getViewState().showMessage(App.getInstance().getString(R.string.user_phone_fragment_send_code_error_text));
                    });
        }
    }

    @Override
    public void onDestroy() {
        if(loadData != null) loadData.dispose();
        super.onDestroy();
    }
}
