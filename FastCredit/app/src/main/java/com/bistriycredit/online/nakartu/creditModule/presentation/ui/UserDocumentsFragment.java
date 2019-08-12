package com.bistriycredit.online.nakartu.creditModule.presentation.ui;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.button.MaterialButton;
import com.bistriycredit.online.nakartu.App;
import com.bistriycredit.online.nakartu.R;
import com.bistriycredit.online.nakartu.creditModule.presentation.presenter.UserDocumentsFragmentPresenter;
import com.bistriycredit.online.nakartu.creditModule.presentation.view.UserDocumentsView;

public class UserDocumentsFragment extends MvpAppCompatFragment implements UserDocumentsView {

    @InjectPresenter
    UserDocumentsFragmentPresenter mPresenter;
    private AppCompatEditText etIPN;
    private AppCompatEditText etPassport;
    private MaterialButton btnBirthDate, btnNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_documents, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view, savedInstanceState);
    }

    private void initUI(View v, Bundle savedInstanceState) {
        etIPN = v.findViewById(R.id.et_user_documents_fragment_ipn);
        etIPN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.setUserIPN(s.toString());
            }
        });
        etPassport = v.findViewById(R.id.et_user_documents_fragment_passport);
        etPassport.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.setUserPassport(s.toString());
            }
        });
        btnBirthDate = v.findViewById(R.id.btn_user_documents_fragment_birth_date);
        btnBirthDate.setOnClickListener(mPresenter::onBtnDateClicked);
        btnNext = v.findViewById(R.id.btn_user_documents_fragment_next);
        btnNext.setOnClickListener(v1 -> {
            String ipn = etIPN.getText().toString();
            String passport = etPassport.getText().toString();
            mPresenter.onBtnNextClicked(ipn, passport);
        });

    }

    @Override
    public void updateUI() {
        mPresenter.btnBirthDateTitle.observe(this, btnBirthDate::setText);
        mPresenter.visibilityBtnNext.observe(this, btnNext::setVisibility);
    }

    @Override
    public void showBirthDatePickerDialog() {
        SpinnerDatePickerDialog dialog = new SpinnerDatePickerDialog();
        dialog.setDateSetListener((view, year, month, dayOfMonth) -> mPresenter.setUserBirthDay(year, month, dayOfMonth));
        dialog.show(getFragmentManager(), "dateDialog");
    }

    @Override
    public void showMessageDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle(App.getInstance().getString(R.string.user_documents_fragment_alert_dialog_title))
                .setMessage(s)
                .setNegativeButton(App.getInstance().getString(R.string.user_documents_fragment_alert_dialog_button_cancel_title), (dialog, which) -> {
                    mPresenter.onCancelBtnDialogClicked();
                    dialog.cancel();
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
