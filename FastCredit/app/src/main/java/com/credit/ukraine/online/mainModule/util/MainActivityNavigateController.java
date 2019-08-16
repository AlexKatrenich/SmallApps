package com.credit.ukraine.online.mainModule.util;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.credit.ukraine.online.mainModule.presentation.presenter.MainActivityPresenter;

public class MainActivityNavigateController {
    private static final MainActivityNavigateController ourInstance = new MainActivityNavigateController();
    private static final String TAG = "MainActivityNC";
    private MainActivityPresenter mMainActivityPresenter;

    public static MainActivityNavigateController getInstance() {
        return ourInstance;
    }

    public void setMainActivityPresenter(MainActivityPresenter presenter){
        mMainActivityPresenter = presenter;
    }

    public void removeMainActivityPresenter(){
        mMainActivityPresenter = null;
    }

    private MainActivityNavigateController() {

    }

    public void navigate(Integer resID){
        Log.i(TAG, "navigate: " + resID);
        if(mMainActivityPresenter != null) mMainActivityPresenter.updateNavigation(resID);
    }

    public void navigate(Integer resID, Bundle bundle) {
        if (bundle == null) {
            navigate(resID);
        } else {
            if(mMainActivityPresenter != null) mMainActivityPresenter.updateNavigation(resID, bundle);
        }

    }

    public void showProgress() {
        if(mMainActivityPresenter != null ) {
            mMainActivityPresenter
                    .progressVisibility
                    .setValue(View.VISIBLE);
        }
    }

    public void hideProgress(){
        if(mMainActivityPresenter != null ) {
            mMainActivityPresenter
                    .progressVisibility
                    .setValue(View.GONE);
        }
    }

    public void navigateBack() {
        if(mMainActivityPresenter != null) mMainActivityPresenter.updateNavigation(-1);
    }

    public void showBackButton() {
        if(mMainActivityPresenter != null ) {
            mMainActivityPresenter
                    .btnBackVisibility
                    .setValue(View.VISIBLE);
        }
    }

    public void hideBackButton(){
        if(mMainActivityPresenter != null ) {
            Log.i(TAG, "hideBackButton: ");
            mMainActivityPresenter
                    .btnBackVisibility
                    .setValue(View.GONE);
        }
    }


    public void showBottomNavigationMenu() {
        if(mMainActivityPresenter != null ) {
            mMainActivityPresenter
                    .bnvVisibility
                    .setValue(View.VISIBLE);
        }
    }

    public void hideBottomNavigationMenu() {
        if(mMainActivityPresenter != null ) {
            mMainActivityPresenter
                    .bnvVisibility
                    .setValue(View.GONE);
        }
    }
}
