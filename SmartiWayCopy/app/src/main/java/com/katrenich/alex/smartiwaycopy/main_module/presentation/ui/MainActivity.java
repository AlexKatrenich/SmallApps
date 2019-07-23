package com.katrenich.alex.smartiwaycopy.main_module.presentation.ui;

import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.main_module.presentation.presenter.MainActivityPresenter;
import com.katrenich.alex.smartiwaycopy.main_module.presentation.view.MainActivityView;


public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    @InjectPresenter
    MainActivityPresenter mPresenter;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI(savedInstanceState);
    }

    private void initUI(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.tb_main_activity);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showBackButton() {

    }

    @Override
    public void hideBackButton() {

    }

    @Override
    public void bindFragment(Fragment fragment, int resource) {

    }
}
