package com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.ui.InfoDialogFragment;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.view.MainView;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import java.util.Observable;
import java.util.Observer;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> implements Observer {
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

        MainActivityNavigateController.getInstance().addObserver(this);

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

    @Override
    public void update(Observable o, Object arg) {
        Integer resID = null;

        try {
            resID = (Integer) arg;
        } catch (ClassCastException e){}

        if(resID != null) bindCurrentFragment(resID);
    }

    @Override
    public void onDestroy() {
        MainActivityNavigateController.getInstance().deleteObserver(this);
        super.onDestroy();
    }
}
