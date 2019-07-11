package com.katrenich.alex.klara.mainScreen.ui;

import android.databinding.DataBindingUtil;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.databinding.ActivityMainScreenBinding;
import com.katrenich.alex.klara.mainScreen.viewmodel.*;


public class MainScreenActivity extends AppCompatActivity {
    public static final String TAG = "MainScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        ActivityMainScreenBinding mainScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        ClickHandler handler = new ClickHandler();
        mainScreenBinding.setHandler(handler);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctl_main_activity);
            AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.abl_main_activity);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i(TAG, "onOffsetChanged: ");
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitleEnabled(true);
                    Log.i(TAG, "onOffsetChanged: setTitle = Title");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitleEnabled(false); //careful there should a space between double quote otherwise it wont work
                    Log.i(TAG, "onOffsetChanged: setTitle = ' ' ");
                    isShow = false;
                }
            }
        });
    }

}
