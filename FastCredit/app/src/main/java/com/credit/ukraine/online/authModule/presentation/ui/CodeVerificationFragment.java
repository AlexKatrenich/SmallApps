package com.credit.ukraine.online.authModule.presentation.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.button.MaterialButton;
import com.credit.ukraine.online.App;
import com.credit.ukraine.online.R;
import com.credit.ukraine.online.authModule.presentation.presenter.CodeVerificationFragmentPresenter;
import com.credit.ukraine.online.authModule.presentation.view.CodeVerificationView;

public class CodeVerificationFragment extends MvpAppCompatFragment implements CodeVerificationView {

    @InjectPresenter
    CodeVerificationFragmentPresenter mPresenter;
    private TextView tvMessage, btnResendCode;
    private AppCompatEditText etUserVerificationCode;
    private MaterialButton btnChanePhoneNumber;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_code_varification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view, savedInstanceState);

    }

    private void initUI(View v, Bundle savedInstanceState) {
        tvMessage = v.findViewById(R.id.tv_code_verification_fragment_message);
        etUserVerificationCode = v.findViewById(R.id.et_code_verification_fragment_code_enter);
        etUserVerificationCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.verificationCodeEntered(s.toString());
            }
        });

        btnChanePhoneNumber = v.findViewById(R.id.btn_code_verification_fragment_change_phone);
        btnChanePhoneNumber.setOnClickListener(mPresenter::onChangePhoneNumberClicked);

        btnResendCode = v.findViewById(R.id.btn_resend_code);
        btnResendCode.setOnClickListener(mPresenter::onButtonResendCodeClicked);

        if (getArguments() != null) {
            String action = getArguments().getString(App.getInstance().getString(R.string.auth_state_action_name));
            mPresenter.setAction(action);
        }
    }

    @Override
    public void updateUI() {
        mPresenter.messageTextViewData.observe(this, tvMessage::setText);
        mPresenter.btnResendCodeVisibility.observe(this, btnResendCode::setVisibility);
    }

    @Override
    public void showMessage(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle(App.getInstance().getString(R.string.user_phone_fragment_title_message))
                .setMessage(s)
                .setNegativeButton(App.getInstance().getString(R.string.user_phone_fragment_message_title_cancel_button), (dialog, which) -> {
                    dialog.cancel();
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
