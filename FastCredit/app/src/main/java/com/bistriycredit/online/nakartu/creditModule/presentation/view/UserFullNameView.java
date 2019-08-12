package com.bistriycredit.online.nakartu.creditModule.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface UserFullNameView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);
}
