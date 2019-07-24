package com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.ui.InfoDialogFragment;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.view.MainView;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> {
    public static final String TAG = "MainActivityPresenter";
    public MutableLiveData<Integer> btnBackVisibility;
    public MutableLiveData<Integer> progressVisibility;

    public MainActivityPresenter() {
        btnBackVisibility = new MutableLiveData<>();
        btnBackVisibility.setValue(View.GONE);

        progressVisibility = new MutableLiveData<>();
        progressVisibility.setValue(View.GONE);

        getViewState().updateUI();
    }

    public void onBtnInfoClicked(View view) {
        getViewState().showInfoDialog(new InfoDialogFragment());
    }
}
