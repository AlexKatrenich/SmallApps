package com.credit.ukraine.online.authModule.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface PasswordSettingView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);
}
