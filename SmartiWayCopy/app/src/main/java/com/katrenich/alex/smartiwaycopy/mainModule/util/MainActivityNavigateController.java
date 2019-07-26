package com.katrenich.alex.smartiwaycopy.mainModule.util;

import android.util.Log;
import android.view.View;

import com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter.MainActivityPresenter;

import java.util.Observable;

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
}
