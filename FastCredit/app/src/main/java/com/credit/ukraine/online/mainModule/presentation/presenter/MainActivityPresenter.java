package com.credit.ukraine.online.mainModule.presentation.presenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.credit.ukraine.online.R;
import com.credit.ukraine.online.mainModule.presentation.ui.InfoDialogFragment;
import com.credit.ukraine.online.mainModule.presentation.view.MainView;
import com.credit.ukraine.online.mainModule.util.MainActivityNavigateController;


@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> {
    public static final String TAG = "MainActivityPresenter";
    public MutableLiveData<Integer> btnBackVisibility;
    public MutableLiveData<Integer> progressVisibility;
    public MutableLiveData<Integer> bnvVisibility;

    public MainActivityPresenter() {
        Log.i(TAG, "MainActivityPresenter: ");
        init();
    }

    private void init() {
        btnBackVisibility = new MutableLiveData<>();
        btnBackVisibility.setValue(View.GONE);

        progressVisibility = new MutableLiveData<>();
        progressVisibility.setValue(View.GONE);

        bnvVisibility = new MutableLiveData<>();
        bnvVisibility.setValue(View.GONE);

        MainActivityNavigateController.getInstance().setMainActivityPresenter(this);

        getViewState().updateUI();
        Log.i(TAG, "init: ");
    }

    private void bindCurrentFragment(Integer fragmentID) {
        Log.i(TAG, "bindCurrentFragment: " + fragmentID);
        getViewState().bindFragment(fragmentID);
    }

    public void onBtnInfoClicked(View view) {
        getViewState().showInfoDialog(new InfoDialogFragment());
    }

    public void updateNavigation(Integer resID){
        bindCurrentFragment(resID);
    }

    @Override
    public void onDestroy() {
        MainActivityNavigateController.getInstance().removeMainActivityPresenter();
        super.onDestroy();
    }

    public void updateNavigation(Integer resID, Bundle bundle) {
        getViewState().bindFragment(resID, bundle);
    }

    public void onCreditBottomNavigationClicked() {
        updateNavigation(R.id.action_menu_to_credit);
    }

    public void onMenuBottomNavigationClicked() {
        updateNavigation(R.id.action_credit_to_menu);
    }
}
