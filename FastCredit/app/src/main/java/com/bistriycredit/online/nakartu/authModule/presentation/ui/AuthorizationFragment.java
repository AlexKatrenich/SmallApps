package com.bistriycredit.online.nakartu.authModule.presentation.ui;

import android.os.Bundle;
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
import com.bistriycredit.online.nakartu.authModule.presentation.presenter.AuthorizationFragmentPresenter;
import com.bistriycredit.online.nakartu.authModule.presentation.view.AuthorizationView;
import com.bistriycredit.online.nakartu.utils.PrefixAppCompatEditText;

public class AuthorizationFragment extends MvpAppCompatFragment implements AuthorizationView {

    private static final String TAG = "AuthorizationFragment";

    @InjectPresenter
    AuthorizationFragmentPresenter mPresenter;

    private PrefixAppCompatEditText etUserPhone;
    private AppCompatEditText etUserPass;
    private MaterialButton btnAuth, btnPassRecover;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authorization, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view, savedInstanceState);
    }

    private void initUI(View v, Bundle savedInstanceState) {
        etUserPass = v.findViewById(R.id.et_password_authorization_fragment);
        etUserPhone = v.findViewById(R.id.et_user_phone_authorization_fragment);
        etUserPhone.setHint(" " + getString(R.string.authorization_fragment_phone_et_hint));
        btnAuth = v.findViewById(R.id.btn_auth_authorization_fragment);
        btnAuth.setOnClickListener(v1 -> mPresenter.onButtonAuthClicked(etUserPhone.getText().toString(), etUserPass.getText().toString()));
        btnPassRecover = v.findViewById(R.id.btn_password_recover_authorization_fragment);
        btnPassRecover.setOnClickListener(mPresenter::onButtonPassRecoverClicked);
    }

    @Override
    public void updateUI(){
        mPresenter.userPhoneNumber.observe(this, etUserPhone::setText);
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
