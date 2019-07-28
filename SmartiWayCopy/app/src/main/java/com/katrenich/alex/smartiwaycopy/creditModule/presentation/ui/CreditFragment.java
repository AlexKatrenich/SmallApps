package com.katrenich.alex.smartiwaycopy.creditModule.presentation.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.button.MaterialButton;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter.CreditFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.view.CreditSelectionView;

public class CreditFragment extends MvpAppCompatFragment implements CreditSelectionView {
    public static final String TAG = "CreditFragment";

    @InjectPresenter
    CreditFragmentPresenter mPresenter;

    private MaterialButton btnGetCredit;
    private AppCompatImageButton btnCreditInfo;
    private NumberPicker npTerms, npMoney;
    private TextView tvTermOfCredit, tvCreditFinalPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_credit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view, savedInstanceState);
    }

    private void initUI(View v, Bundle savedInstanceState) {
        hideKeyboard(v);

        btnGetCredit = v.findViewById(R.id.btn_credit_fragment_get_credit);
        btnCreditInfo = v.findViewById(R.id.btn_info_credit_fragment);
        tvCreditFinalPrice = v.findViewById(R.id.tv_credit_fragment_final_price);
        tvTermOfCredit = v.findViewById(R.id.tv_credit_fragment_term);

        npMoney = v.findViewById(R.id.np_credit_fragment_price);
        npMoney.setWrapSelectorWheel(false);
        npMoney.setOnValueChangedListener((picker, oldVal, newVal) -> mPresenter.newCashValueSelected(newVal));

        npTerms = v.findViewById(R.id.np_credit_fragment_term);
        npTerms.setWrapSelectorWheel(false);
        npTerms.setOnValueChangedListener((picker, oldVal, newVal) -> mPresenter.newRangeOfCreditSelected(newVal + 1));


    }

    private void hideKeyboard(View v) {
        InputMethodManager inputManager = (InputMethodManager) v
                .getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        IBinder binder = v.getWindowToken();
        inputManager.hideSoftInputFromWindow(binder,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void updateUI() {
        mPresenter.creditSumRange.observe(this, strings -> {
            npMoney.setMinValue(0);
            npMoney.setMaxValue(strings.length - 1);
            npMoney.setDisplayedValues(strings);
        });

        mPresenter.termRange.observe(this, strings -> {
            npTerms.setMaxValue(0);
            npTerms.setMaxValue(strings.length - 1);
            npTerms.setDisplayedValues(strings);
        });

        mPresenter.termCountValue.observe(this, tvTermOfCredit::setText);
        mPresenter.creditCashValue.observe(this, tvCreditFinalPrice::setText);
    }
}
