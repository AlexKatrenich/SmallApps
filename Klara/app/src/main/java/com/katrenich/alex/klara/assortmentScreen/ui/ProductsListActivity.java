package com.katrenich.alex.klara.assortmentScreen.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.assortmentScreen.viewmodel.ProductListViewModel;
import com.katrenich.alex.klara.databinding.ActivityProductsListBinding;


public class ProductsListActivity extends AppCompatActivity {
    private static final String TAG = "ProductsListActivity";

    private ProductListViewModel mViewModel;
    protected Toolbar mToolbar;

    protected BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        ActivityProductsListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list);
        if(savedInstanceState == null){
            mViewModel.init();
        }
        binding.setModel(mViewModel);
        mToolbar = findViewById(R.id.tb_product_list_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupListUpdate();

        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        mBottomNavigationView = findViewById(R.id.bnv_product_list_activity);
        mBottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.bnv_menu_all_item: {
                    mViewModel.showAllProducts();
                    return true;
                }
                case R.id.bnv_menu_cake_item: {
                    mViewModel.showCakeProducts();
                    return true;
                }
                case R.id.bnv_menu_drinks_item: {
                    mViewModel.showDrinkProducts();
                    return true;
                }
                case R.id.bnv_menu_patty_item: {
                    mViewModel.showPattyProducts();
                    return true;
                }
                case R.id.bnv_menu_salad_item: {
                    mViewModel.showSaladProducts();
                    return true;
                }
            }
            return false;
        });

        // restore bottom navigation view state and recycler view state
        if(savedInstanceState != null) {
            Integer i = mViewModel.restoreBnvState();
            if (i != null) {
                String s = String.format("Salad button ID: %d, restored id: %d", R.id.bnv_menu_salad_item, i);
                Log.i(TAG, "init: " + s);
                switch (i){
                    case R.id.bnv_menu_all_item: {
                        mViewModel.showAllProducts();
                        break;
                    }
                    case R.id.bnv_menu_cake_item: {
                        mViewModel.showCakeProducts();
                        break;
                    }
                    case R.id.bnv_menu_drinks_item: {
                        mViewModel.showDrinkProducts();
                        break;
                    }
                    case R.id.bnv_menu_patty_item: {
                        mViewModel.showPattyProducts();
                        break;
                    }
                    case R.id.bnv_menu_salad_item: {
                        mViewModel.showSaladProducts();
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mViewModel != null && mBottomNavigationView != null){
            mViewModel.saveBnvState(mBottomNavigationView.getSelectedItemId());
        }
        super.onSaveInstanceState(outState);
    }

    private void setupListUpdate() {
        mViewModel.loading.set(View.VISIBLE);
        mViewModel.dataWasLoaded.observe(this, aBoolean -> mViewModel.fetchList());
        mViewModel.getProducts().observe(this, products -> {
            mViewModel.loading.set(View.GONE);
            Log.i(TAG, "setupListUpdate: " + products);
            mViewModel.setProductsInAdapter(products);
        });
    }


    @Override
    protected void onDestroy() {
        setSupportActionBar(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }
}
