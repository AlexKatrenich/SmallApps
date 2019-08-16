package com.credit.ukraine.online.authModule.presentation.ui;

import android.graphics.PorterDuff;
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
import com.credit.ukraine.online.App;
import com.credit.ukraine.online.R;
import com.credit.ukraine.online.authModule.presentation.presenter.PasswordSettingFragmentPresenter;
import com.credit.ukraine.online.authModule.presentation.view.PasswordSettingView;

public class PasswordSettingFragment extends MvpAppCompatFragment implements PasswordSettingView {

    private static final String TAG = "PasswordSettingFragment";
    @InjectPresenter
    PasswordSettingFragmentPresenter mPresenter;

    private AppCompatEditText etPassword, etPasswordConfirm;
    private MaterialButton btnConfirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view, savedInstanceState);
    }

    private void initUI(View v, Bundle savedInstanceState) {

        if(getArguments() != null){
            String action = getArguments().getString(getString(R.string.auth_state_action_name));
            mPresenter.setAuthAction(action);
        }

        etPassword = v.findViewById(R.id.et_password_setting_fragment_enter_password);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean b = mPresenter.verifyPass(s.toString());
                if(!b) {
                    etPassword.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                } else {
                    etPassword.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
        etPasswordConfirm = v.findViewById(R.id.et_password_setting_fragment_confirm_password);
        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean b = mPresenter.verifyPass(s.toString());

                String pass = etPassword.getText().toString();
                String passConf = etPasswordConfirm.getText().toString();

                if(b && pass.equals(passConf)) {
                    etPasswordConfirm.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else {
                    etPasswordConfirm.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        btnConfirm = v.findViewById(R.id.btn_password_setting_fragment);
        btnConfirm.setOnClickListener(v1 -> mPresenter.onButtonConfirmClicked(etPassword.getText().toString(), etPasswordConfirm.getText().toString()));
    }

    @Override
    public void showMessage(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(App.getInstance());
        builder.setTitle(App.getInstance().getString(R.string.user_phone_fragment_title_message))
                .setMessage(s)
                .setNegativeButton(App.getInstance().getString(R.string.user_phone_fragment_message_title_cancel_button), (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
