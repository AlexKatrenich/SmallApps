package com.credit.ukraine.online.creditModule.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface UserDocumentsView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showBirthDatePickerDialog();

    @StateStrategyType(SkipStrategy.class)
    void showMessageDialog(String message);

    void updateUI();

}
