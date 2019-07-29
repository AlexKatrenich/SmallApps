package com.katrenich.alex.smartiwaycopy.creditModule.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface UserFullNameView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);
}
