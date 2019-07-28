package com.katrenich.alex.smartiwaycopy.creditModule.presentation.ui;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
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

    private LinearLayout btnSetCreditTerm, btnSetCreditValue;
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

    // initialize all view`s here
    private void initUI(View v, Bundle savedInstanceState) {
        hideKeyboard(v);

        btnGetCredit = v.findViewById(R.id.btn_credit_fragment_get_credit);
        btnGetCredit.setOnClickListener(mPresenter::onBtnGetCreditClicked);

        btnCreditInfo = v.findViewById(R.id.btn_info_credit_fragment);
        btnCreditInfo.setOnClickListener(mPresenter::onBtnCreditInfoClicked);

        tvCreditFinalPrice = v.findViewById(R.id.tv_credit_fragment_final_price);
        tvTermOfCredit = v.findViewById(R.id.tv_credit_fragment_term);

        npMoney = v.findViewById(R.id.np_credit_fragment_price);
        npMoney.setWrapSelectorWheel(false);
        npMoney.setOnValueChangedListener((picker, oldVal, newVal) -> mPresenter.newCashValueSelected(newVal));

        npTerms = v.findViewById(R.id.np_credit_fragment_term);
        npTerms.setWrapSelectorWheel(false);
        npTerms.setOnValueChangedListener((picker, oldVal, newVal) -> mPresenter.newRangeOfCreditSelected(newVal + 1));

        btnSetCreditValue = v.findViewById(R.id.ll_credit_fragment_btn_sum_credit);
        btnSetCreditValue.setOnClickListener(mPresenter::onSetCreditValueClicked);
        btnSetCreditTerm = v.findViewById(R.id.ll_credit_fragment_btn_term_credit);
        btnSetCreditTerm.setOnClickListener(mPresenter::onSetCreditTermClicked);

    }

    // method for hiding keyboard if that on the screen
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
        // observe changes to set new range in TermNumberPicker
        mPresenter.creditSumRange.observe(this, strings -> {
            npMoney.setMinValue(0);
            npMoney.setMaxValue(strings.length - 1);
            npMoney.setDisplayedValues(strings);
        });

        // observe changes to set new range in TermNumberPicker
        mPresenter.termRange.observe(this, strings -> {
            npTerms.setMaxValue(0);
            npTerms.setMaxValue(strings.length - 1);
            npTerms.setDisplayedValues(strings);
        });

        // observe changes when CreditTermNumberPicker scrolling
        mPresenter.termCountValue.observe(this, tvTermOfCredit::setText);

        // observe changes when CreditCashNumberPicker scrolling
        mPresenter.creditCashValue.observe(this, tvCreditFinalPrice::setText);
    }

    @Override
    public void showCreditInfo(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getResources().getString(R.string.credit_fragment_info_dialog_caption))
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(getString(R.string.credit_fragment_info_dialog_btn_next_caption), (dialog, which) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showSetCreditSumDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AppCompatEditText input = new AppCompatEditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // setup buttons
        builder.setPositiveButton("Підтвердити", (dialog, which) -> {
            String s = input.getText().toString();
            mPresenter.setNewCreditSum(s);
        });
        builder.setNegativeButton("Скасувати", (dialog, which) -> dialog.cancel());
        builder.setTitle(getString(R.string.credit_fragment_set_credit_value_dialog_title));
        builder.setMessage(getString(R.string.credit_fragment_set_credit_value_manually_text));

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showSetCreditTermDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // setup input view to dialog
        final AppCompatEditText input = new AppCompatEditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // setup buttons
        builder.setPositiveButton("Підтвердити", (dialog, which) -> {
            String s = input.getText().toString();
            mPresenter.setNewTermCredit(s);
        });
        builder.setNegativeButton("Скасувати", (dialog, which) -> dialog.cancel());
        builder.setTitle(getString(R.string.credit_fragment_set_term_dialog_title));
        builder.setMessage(getString(R.string.credit_fragment_set_credit_term_manually_text));

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void scrollTermToValue(int term) {
        npTerms.setValue(term - 1); // term - 1 => because NumberPikers rang starts from 0
    }

    @Override
    public void scrollCreditSumToValue(int position) {
        npMoney.setValue(position);
    }
}
