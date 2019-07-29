package com.katrenich.alex.smartiwaycopy.creditModule.presentation.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.button.MaterialButton;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter.UserDocumentsFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.view.UserDocumentsView;
import com.santalu.maskedittext.MaskEditText;

import java.util.Calendar;

public class UserDocumentsFragment extends MvpAppCompatFragment implements UserDocumentsView {

    @InjectPresenter
    UserDocumentsFragmentPresenter mPresenter;
    private AppCompatEditText etIPN;
    private MaskEditText etPassport;
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
        etPassport = v.findViewById(R.id.et_user_documents_fragment_passport);
        btnBirthDate = v.findViewById(R.id.btn_user_documents_fragment_birth_date);
        btnBirthDate.setOnClickListener(mPresenter::onBtnDateClicked);
        btnNext = v.findViewById(R.id.btn_user_documents_fragment_next);
        btnNext.setOnClickListener(v1 -> {
            String ipn = etIPN.getText().toString();
            String passport = etPassport.getRawText();
            mPresenter.onBtnNextClicked(ipn, passport);
        });
    }

    @Override
    public void updateUI() {
        mPresenter.btnBirthDateTitle.observe(this, s -> {
            btnBirthDate.setText(s);
            btnBirthDate.setTextColor(App.getInstance().getResources().getColor(android.R.color.white));
            btnBirthDate.setBackgroundTintList(ContextCompat.getColorStateList(this.getContext(), R.color.colorAccent));
        });
    }

    @Override
    public void showBirthDatePickerDialog() {
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        try {
            Context context = this.getContext();
            if(context == null) return;
            DatePickerDialog dialog = new DatePickerDialog(
                    this.getContext(),
                    0,
                    (view, year1, month1, dayOfMonth) -> mPresenter.setUserBirthDay(year1, month1, dayOfMonth),
                    year, month, day);
            dialog.getDatePicker().setMaxDate(Calendar.getInstance().getTime().getTime());
            dialog.show();
        } catch (NullPointerException e){

        }
    }

    @Override
    public void showMessageDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle(App.getInstance().getString(R.string.user_documents_fragment_alert_dialog_title))
                .setMessage(s)
                .setNegativeButton(App.getInstance().getString(R.string.user_documents_fragment_alert_dialog_button_cancel_title), (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
