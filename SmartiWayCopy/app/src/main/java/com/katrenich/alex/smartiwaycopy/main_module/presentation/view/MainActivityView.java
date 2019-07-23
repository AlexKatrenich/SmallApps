package com.katrenich.alex.smartiwaycopy.main_module.presentation.view;


import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpView;

public interface MainActivityView extends MvpView {
    void showProgress();
    void hideProgress();
    void showBackButton();
    void hideBackButton();
    void bindFragment(Fragment fragment, int resource);
}
