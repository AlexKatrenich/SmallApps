package com.credit.ukraine.online.creditModule.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface CreditSelectionView extends MvpView {

    void updateUI();

    void showCreditInfo(String message);

    void showSetCreditSumDialog();

    void showSetCreditTermDialog();

    void scrollTermToValue(int position);

    void scrollCreditSumToValue(int position);
}
