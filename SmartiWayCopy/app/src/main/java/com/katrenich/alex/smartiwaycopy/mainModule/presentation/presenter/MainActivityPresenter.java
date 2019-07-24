package com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.support.v4.app.Fragment;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.ui.InfoDialogFragment;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.view.MainView;

import java.util.Observable;
import java.util.Observer;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> implements Observer {
    public static final String TAG = "MainActivityPresenter";
    public MutableLiveData<Integer> btnBackVisibility;
    public MutableLiveData<Integer> progressVisibility;

    public MainActivityPresenter() {
        init();
    }

    private void init() {
        btnBackVisibility = new MutableLiveData<>();
        btnBackVisibility.setValue(View.GONE);

        progressVisibility = new MutableLiveData<>();
        progressVisibility.setValue(View.GONE);

        AuthController.getInstance().addObserver(this);

        bindFragment();
        getViewState().updateUI();
    }

    private void bindFragment() {
        Fragment fragment = null;
        if(!AuthController.getInstance().isUserLogged()){
            fragment = new InfoDialogFragment();
        } else {

        }
        bindCurrentFragment(fragment);
    }

    private void bindCurrentFragment(Fragment fragment) {
        getViewState().bindFragment(fragment, R.id.fl_fragment_container_main_activity);
    }

    public void onBtnInfoClicked(View view) {
        getViewState().showInfoDialog(new InfoDialogFragment());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
