package com.katrenich.alex.smartiwaycopy.authModule.presentation.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.button.MaterialButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter.UserPhoneFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.UserPhoneView;
import com.santalu.maskedittext.MaskEditText;

public class UserPhoneFragment extends MvpAppCompatFragment implements UserPhoneView {

    private static final String TAG = "UserPhoneFragment";
    @InjectPresenter
    UserPhoneFragmentPresenter mPresenter;

    private MaskEditText etUserPhone;
    private TextView btnPolicyLicence;
    private MaterialButton btnAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view, savedInstanceState);
    }

    private void initUI(View v, Bundle savedInstanceState) {
        etUserPhone = v.findViewById(R.id.et_user_phone_input_fragment);
        etUserPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = "+38" + etUserPhone.getRawText();
                if(phone.length() == 13) {
                    Log.i(TAG, "afterTextChanged: " + phone);
                }
            }
        });
        btnPolicyLicence = v.findViewById(R.id.tv_user_phone_fragment_license_btn);
        btnPolicyLicence.setOnClickListener(mPresenter::onPolicyButtonClicked);
        btnAuth = v.findViewById(R.id.btn_user_phone_fragment_auth);
        btnAuth.setOnClickListener(mPresenter::onButtonAuthClicked);
    }
}
