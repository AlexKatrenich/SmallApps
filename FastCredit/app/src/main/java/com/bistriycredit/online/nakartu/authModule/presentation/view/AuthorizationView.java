package com.bistriycredit.online.nakartu.authModule.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface AuthorizationView extends MvpView {

    void updateUI();

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String string);
}
