package com.credit.ukraine.online.mainModule.presentation.ui;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.credit.ukraine.online.R;
import com.credit.ukraine.online.mainModule.presentation.presenter.MainActivityPresenter;
import com.credit.ukraine.online.mainModule.presentation.view.MainView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    @InjectPresenter
    MainActivityPresenter mPresenter;

    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private ImageButton btnBack, btnInfo;
    private BottomNavigationView mBnv;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI(savedInstanceState);

        // THIS TEMPORARY CODE, DELETE OR COMMENT THIS,
        // AFTER GETTING HASH KEY
        printKeyHash(this);

        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
    }

    // THIS TEMPORARY CODE, DELETE OR COMMENT THIS,
    // AFTER GETTING HASH KEY
    private void printKeyHash(Context context) {
        try {
            PackageInfo info = getPackageManager()
                    .getPackageInfo(getPackageName(),//"com.bistriycredit.online.nakartu",
                            PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d(TAG, "KeyHash: " +
                        Base64.encodeToString(
                                md.digest(),
                                Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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

        mBnv = findViewById(R.id.bnv_main_activity);
//        mBnv.setOnNavigationItemSelectedListener(menuItem -> {
//            if(getSelectedItemID(mBnv) == menuItem.getItemId()) return false;
//
//            if (menuItem.getItemId() == R.id.item_credit){
//                mPresenter.onCreditBottomNavigationClicked();
//                return true;
//            } else if(menuItem.getItemId() == R.id.item_menu){
//                mPresenter.onMenuBottomNavigationClicked();
//                return true;
//            }
//
//            return false;
//        });
        mNavController = Navigation.findNavController(this, R.id.nav_controller_main_activity);
    }

    @Override
    public void updateUI() {
        mPresenter.progressVisibility.observe(this, mProgressBar::setVisibility);
        mPresenter.btnBackVisibility.observe(this, btnBack::setVisibility);
        mPresenter.bnvVisibility.observe(this, mBnv::setVisibility);
    }

    @Override
    public void onBackPressed() {
        mProgressBar.setVisibility(View.GONE);
        // don`t respond while pressed back-button on Menu or Credit screen
        if(mNavController.getCurrentDestination().getId() == R.id.creditFragment || mNavController.getCurrentDestination().getId() == R.id.menuFragment) return;

        super.onBackPressed();

        if(mNavController.getCurrentDestination().getId() == R.id.creditFragment || mNavController.getCurrentDestination().getId() == R.id.menuFragment){
            mPresenter.bnvVisibility.setValue(View.VISIBLE);
            mPresenter.btnBackVisibility.setValue(View.GONE);
        }
    }

    @Override
    public void bindFragment(Integer destID) {
        if (destID == null) {
            return;
        }

        if(destID == -1) {
            mNavController.navigateUp();
            return;
        }


        if(mNavController.getCurrentDestination().getId() != destID){
            mNavController.navigate(destID);
        }
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

    @Override
    public void bindFragment(Integer resID, Bundle bundle) {
        mNavController.navigate(resID, bundle);
    }

    private int getSelectedItemID(BottomNavigationView bottomNavigationView) {
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.isChecked()) {
                return menuItem.getItemId();
            }
        }
        return 0;
    }

}
