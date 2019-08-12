package com.bistriycredit.online.nakartu.creditModule.presentation.ui;

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
import com.bistriycredit.online.nakartu.creditModule.presentation.presenter.UserFullNameFragmentPresenter;
import com.bistriycredit.online.nakartu.creditModule.presentation.view.UserFullNameView;

public class UserFullNameFragment extends MvpAppCompatFragment implements UserFullNameView {

    @InjectPresenter
    UserFullNameFragmentPresenter mPresenter;

    private AppCompatEditText etUserFirstName, etUserLastName, etUserMiddleName;
    private MaterialButton btnNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_full_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view, savedInstanceState);
    }

    private void initUI(View v, Bundle savedInstanceState) {
        etUserFirstName = v.findViewById(R.id.et_user_full_name_fragment_first_name);
        etUserLastName = v.findViewById(R.id.et_user_full_name_fragment_last_name);
        etUserMiddleName = v.findViewById(R.id.et_user_full_name_fragment_middle_name);
        btnNext = v.findViewById(R.id.btn_user_full_name_fragment_next);
        btnNext.setOnClickListener(v1 -> {
            String firstName = etUserFirstName.getText().toString();
            String lastName = etUserLastName.getText().toString();
            String middleName = etUserMiddleName.getText().toString();
            mPresenter.onBtnNextClicked(firstName, middleName, lastName);
        });
    }

    @Override
    public void showMessage(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle(App.getInstance().getString(R.string.user_full_name_fragment_alert_dialog_title))
                .setMessage(s)
                .setNegativeButton(App.getInstance().getString(R.string.user_full_name_fragment_alert_dialog_button_cancel_title), (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
