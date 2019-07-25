package com.katrenich.alex.smartiwaycopy.creditModule.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter.CreditFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.view.CreditSelectionView;

public class CreditFragment extends MvpAppCompatFragment implements CreditSelectionView {

    @InjectPresenter
    CreditFragmentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_credit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view, savedInstanceState);
    }

    private void initUI(View view, Bundle savedInstanceState) {

    }
}
