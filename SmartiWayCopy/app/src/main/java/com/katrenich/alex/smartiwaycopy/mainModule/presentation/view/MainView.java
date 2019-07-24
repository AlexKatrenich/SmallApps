package com.katrenich.alex.smartiwaycopy.mainModule.presentation.view;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    void updateUI();
    void bindFragment(Fragment fragment, int containerId);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showInfoDialog(DialogFragment dialogFragment);
}
