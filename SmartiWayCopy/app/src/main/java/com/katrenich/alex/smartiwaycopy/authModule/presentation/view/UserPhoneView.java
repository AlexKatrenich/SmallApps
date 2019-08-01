package com.katrenich.alex.smartiwaycopy.authModule.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface UserPhoneView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showMessage(String s);

    void updateUI();
}
