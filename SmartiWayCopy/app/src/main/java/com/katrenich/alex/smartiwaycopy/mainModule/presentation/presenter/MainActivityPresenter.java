package com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.ui.UserPhoneFragment;
import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.ui.InfoDialogFragment;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.view.MainView;
import com.katrenich.alex.smartiwaycopy.utils.AppStates;
import com.katrenich.alex.smartiwaycopy.mainModule.util.UserActionController;

import java.util.Observable;
import java.util.Observer;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> implements Observer{
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

        UserActionController.getInstance().addObserver(this);
        UserActionController.getInstance().startApp();

        getViewState().updateUI();
        Log.i(TAG, "init: ");
    }

    private void bindCurrentFragment(Fragment fragment) {
        getViewState().bindFragment(fragment, R.id.fl_fragment_container_main_activity);
    }

    public void onBtnInfoClicked(View view) {
        getViewState().showInfoDialog(new InfoDialogFragment());
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.i(TAG, "update: start");
        AppStates states = AppStates.REGISTER_PHONE_ENTER;
        try {
             states = (AppStates) arg;
        } catch (ClassCastException e){
            Log.e(TAG, "update: ", e);
        }

        switch (states){
            case REGISTER_PHONE_ENTER: {
                bindCurrentFragment(new UserPhoneFragment());
                Log.i(TAG, "update: REGISTER_PHONE_ENTER");
                break;
            }
            case AUTHORIZATION: {
                Log.i(TAG, "update: AUTHORIZATION");
                break;
            }
            case PASSWORD_RECOVER: {
                break;
            }
            case PASSWORD_CREATION: {
                break;
            }
            case SMS_CODE_VALIDATION: {
                break;
            }
            case MAIN_SCREEN_CREDIT:{
                break;
            }
            case MAIN_SCREEN_SETTINGS:{
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void onDestroy() {
        UserActionController.getInstance().deleteObserver(this);
        super.onDestroy();
    }
}
