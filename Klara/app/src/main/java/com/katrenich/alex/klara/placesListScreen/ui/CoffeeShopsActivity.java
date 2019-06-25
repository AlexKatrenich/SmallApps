package com.katrenich.alex.klara.placesListScreen.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.databinding.ActivityCoffeeShopsBinding;
import com.katrenich.alex.klara.placesListScreen.viewmodel.CoffeeShopsViewModel;

public class CoffeeShopsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private CoffeeShopsViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_shops);

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(CoffeeShopsViewModel.class);
        ActivityCoffeeShopsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_coffee_shops);
        if(savedInstanceState == null){
            mViewModel.init();
        }
        binding.setCoffeeShopsModel(mViewModel);


        mToolbar = findViewById(R.id.tb_coffee_shops_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());

        viewUpdate();
    }


    private void viewUpdate(){
        mViewModel.loading.set(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        setSupportActionBar(null);

        super.onDestroy();
    }
}
