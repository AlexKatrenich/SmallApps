package com.katrenich.alex.smartiwaycopy.mainModule.presentation.ui;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter.MainActivityPresenter;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    @InjectPresenter
    MainActivityPresenter mPresenter;

    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private ImageButton btnBack, btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI(savedInstanceState);
    }

    // init UI-elements here
    private void initUI(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.tb_main_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnBack = findViewById(R.id.ib_back_toolbar_main_activity);
        btnBack.setOnClickListener(v -> onBackPressed());

        Log.i(TAG, "initUI: PRESENTER" + mPresenter);
        btnInfo = findViewById(R.id.ib_info_toolbar_main_activity);
        btnInfo.setOnClickListener(mPresenter::onBtnInfoClicked);

        mProgressBar = findViewById(R.id.pb_toolbar_main_activity);
    }

    @Override
    public void updateUI() {
        mPresenter.progressVisibility.observe(this, mProgressBar::setVisibility);
        mPresenter.btnBackVisibility.observe(this, btnBack::setVisibility);
    }

    @Override
    public void bindFragment(Fragment fragment, int containerId) {
        if (fragment == null || containerId == 0) {
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showInfoDialog(DialogFragment dialogFragment) {
        String infoDialogTag = "infoDialog";

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(infoDialogTag);
        if (prev != null) {
            ft.remove(prev);
        }

        ft.addToBackStack(null);

        dialogFragment.show(getSupportFragmentManager(), infoDialogTag);
    }

}
