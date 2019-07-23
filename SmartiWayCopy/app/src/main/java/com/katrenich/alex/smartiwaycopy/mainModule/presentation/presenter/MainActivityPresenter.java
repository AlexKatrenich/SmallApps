package com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
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
        if(progressVisibility.getValue() == View.GONE){
            progressVisibility.setValue(View.VISIBLE);
            Log.i(TAG, "onBtnInfoClicked: SET VISIBLE");
        } else {
            Log.i(TAG, "onBtnInfoClicked: SET GONE");
            progressVisibility.setValue(View.GONE);
        }
    }
}
