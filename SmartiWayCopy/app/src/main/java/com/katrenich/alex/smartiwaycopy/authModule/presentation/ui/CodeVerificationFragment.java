package com.katrenich.alex.smartiwaycopy.authModule.presentation.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter.CodeVerificationFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.CodeVerificationView;

public class CodeVerificationFragment extends MvpAppCompatFragment implements CodeVerificationView {

    @InjectPresenter
    CodeVerificationFragmentPresenter mPresenter;
    private TextView tvMessage;
    private AppCompatEditText etUserVerificationCode;


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
    }

    @Override
    public void updateUI() {
        mPresenter.messageTextViewData.observe(this, tvMessage::setText);
    }
}
