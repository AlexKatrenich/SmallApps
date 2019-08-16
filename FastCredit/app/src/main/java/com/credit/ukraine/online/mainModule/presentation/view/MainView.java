package com.credit.ukraine.online.mainModule.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {
    void updateUI();
    void bindFragment(Integer id);
    void showInfoDialog(DialogFragment dialogFragment);

    void bindFragment(Integer resID, Bundle bundle);
}
