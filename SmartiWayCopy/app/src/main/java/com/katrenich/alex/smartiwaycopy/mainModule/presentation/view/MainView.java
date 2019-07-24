package com.katrenich.alex.smartiwaycopy.mainModule.presentation.view;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {
    void updateUI();
    void bindFragment(Fragment fragment, int containerId);
    void showInfoDialog(DialogFragment dialogFragment);
}
