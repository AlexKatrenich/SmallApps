package com.katrenich.alex.smartiwaycopy.mainModule.presentation.view;

import androidx.fragment.app.DialogFragment;


import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {
    void updateUI();
    void bindFragment(Integer id);
    void showInfoDialog(DialogFragment dialogFragment);
}
