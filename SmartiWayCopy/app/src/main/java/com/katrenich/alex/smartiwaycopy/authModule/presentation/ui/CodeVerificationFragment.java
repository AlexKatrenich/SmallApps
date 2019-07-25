package com.katrenich.alex.smartiwaycopy.authModule.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.presenter.CodeVerificationFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.authModule.presentation.view.CodeVerificationView;

public class CodeVerificationFragment extends MvpAppCompatFragment implements CodeVerificationView {

    @InjectPresenter
    CodeVerificationFragmentPresenter mPresenter;

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

    }
}
