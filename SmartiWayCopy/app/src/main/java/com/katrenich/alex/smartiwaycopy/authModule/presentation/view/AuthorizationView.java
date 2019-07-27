package com.katrenich.alex.smartiwaycopy.authModule.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface AuthorizationView extends MvpView {

    void updateUI();

    void showMessage(String string);
}
