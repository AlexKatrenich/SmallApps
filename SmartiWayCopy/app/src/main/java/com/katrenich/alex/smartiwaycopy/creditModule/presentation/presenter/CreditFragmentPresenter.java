package com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.view.CreditSelectionView;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

@InjectViewState
public class CreditFragmentPresenter extends MvpPresenter<CreditSelectionView> {

    public CreditFragmentPresenter() {
        initPresenter();
    }

    private void initPresenter() {
        MainActivityNavigateController.getInstance().hideBackButton();
    }



}
