package com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.ui.PasswordSettingFragment;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.ui.InfoDialogFragment;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.view.MainView;

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

        getViewState().updateUI();
        bindCurrentFragment(new PasswordSettingFragment());
        Log.i(TAG, "init: ");
    }

    private void bindCurrentFragment(Fragment fragment) {
        getViewState().bindFragment(fragment, R.id.fl_fragment_container_main_activity);
    }

    public void onBtnInfoClicked(View view) {
        getViewState().showInfoDialog(new InfoDialogFragment());
    }

}
