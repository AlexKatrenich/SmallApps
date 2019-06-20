package com.katrenich.alex.klara.mainScreen.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.databinding.ActivityMainScreenBinding;
import com.katrenich.alex.klara.mainScreen.viewmodel.*;


public class MainScreenActivity extends AppCompatActivity {

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
    }

}
