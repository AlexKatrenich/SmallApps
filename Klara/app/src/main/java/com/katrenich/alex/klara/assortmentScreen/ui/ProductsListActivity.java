package com.katrenich.alex.klara.assortmentScreen.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.assortmentScreen.viewmodel.ProductListViewModel;
import com.katrenich.alex.klara.databinding.ActivityProductsListBinding;
import com.katrenich.alex.klara.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListActivity extends AppCompatActivity {
    private static final String TAG = "ProductsListActivity";
    @BindView(R.id.tb_activity_product_list)
    protected Toolbar mToolbar;
    @BindView(R.id.rv_product_list_activity)
    protected RecyclerView rvProductList;

    private ProductListViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        ActivityProductsListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list);
        if(savedInstanceState == null){
            mViewModel.init();
        }
        binding.setModel(mViewModel);
        setupListUpdate();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        Log.i(TAG, "init done");
    }

    private void setupListUpdate() {
        mViewModel.loading.set(View.VISIBLE);
        mViewModel.fetchList();
        mViewModel.getProducts().observe(this, products -> {
            mViewModel.loading.set(View.GONE);
            mViewModel.setProductsInAdapter(products);
        });
    }

}
