package com.katrenich.alex.smartiwaycopy.authModule.presentation.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.button.MaterialButton;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter.UserPhoneFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.UserPhoneView;
import com.katrenich.alex.smartiwaycopy.utils.PrefixAppCompatEditText;

public class UserPhoneFragment extends MvpAppCompatFragment implements UserPhoneView {

    private static final String TAG = "UserPhoneFragment";

    @InjectPresenter
    UserPhoneFragmentPresenter mPresenter;

    private TextWatcher mTextWatcher;

    private PrefixAppCompatEditText etUserPhone;
    private TextView btnPolicyLicence;
    private MaterialButton btnAuth;
    private TextView tvUserMessage, tvLicense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_user_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        initUI(view, savedInstanceState);
    }

    private void initUI(View v, Bundle savedInstanceState) {
        if (getArguments() != null) {
            try {
                String action = getArguments().getString("action");
                mPresenter.setAuthAction(action);
            } catch (ClassCastException e){
                Log.e(TAG, "initUI: ", e);
            }
        } else {
            Log.i(TAG, "onResume: getArguments() == null ");
        }

        Log.i(TAG, "initUI: ");
        etUserPhone = v.findViewById(R.id.et_user_phone_input_fragment);
        etUserPhone.setHint(" " + getString(R.string.user_phone_fragment_input_et_hint));
        mTextWatcher =  new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = s.toString();
                if (s.length() == 9) {
                    Log.i(TAG, "afterTextChanged: " + etUserPhone.getText());
                    etUserPhone.setText("");
                    mPresenter.phoneNumberEntered(phone);
                }

            }
        };

        etUserPhone.addTextChangedListener(mTextWatcher);

        btnPolicyLicence = v.findViewById(R.id.tv_user_phone_fragment_license_btn);
        btnPolicyLicence.setOnClickListener(mPresenter::onPolicyButtonClicked);
        btnAuth = v.findViewById(R.id.btn_user_phone_fragment_auth);
        btnAuth.setOnClickListener(mPresenter::onButtonAuthClicked);
        tvUserMessage = v.findViewById(R.id.tv_user_phone_fragment_user_message);
        tvLicense = v.findViewById(R.id.tv_user_phone_fragment_license);
    }

    @Override
    public void updateUI(){
        Log.i(TAG, "updateUI: ");
        mPresenter.btnAuthVisible.observe(this, btnAuth::setVisibility);
        mPresenter.tvLicenseVisible.observe(this, tvLicense::setVisibility);
        mPresenter.btnPolicyLicenceVisible.observe(this, btnPolicyLicence::setVisibility);
        mPresenter.userMessage.observe(this, tvUserMessage::setText);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        etUserPhone.removeTextChangedListener(mTextWatcher);
        super.onDestroy();
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
