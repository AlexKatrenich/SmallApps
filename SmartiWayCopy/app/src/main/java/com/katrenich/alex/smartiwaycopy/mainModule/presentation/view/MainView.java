package com.katrenich.alex.smartiwaycopy.mainModule.presentation.view;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {
    void updateUI();
    void bindFragment(Fragment fragment, int containerId);
    void showInfoDialog(DialogFragment dialogFragment);
}
