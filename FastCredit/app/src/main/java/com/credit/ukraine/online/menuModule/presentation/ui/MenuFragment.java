package com.credit.ukraine.online.menuModule.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.credit.ukraine.online.R;
import com.credit.ukraine.online.menuModule.presentation.presenter.MenuFragmentPresenter;
import com.credit.ukraine.online.menuModule.presentation.view.MenuView;

public class MenuFragment extends MvpAppCompatFragment implements MenuView {
    public static final String TAG = "MenuFragment";

    @InjectPresenter
    MenuFragmentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view, savedInstanceState);
    }

    private void initUI(View v, Bundle savedInstanceState) {

    }
}
