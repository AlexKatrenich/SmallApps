package com.katrenich.alex.smartiwaycopy.authModule.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface CodeVerificationView extends MvpView {

    void updateUI();

    void showMessage(String message);
}
