package com.katrenich.alex.klara.assortmentScreen.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.assortmentScreen.viewmodel.ProductListActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListActivity extends AppCompatActivity {
    @BindView(R.id.tb_activity_product_list)
    protected Toolbar mToolbar;

    private ProductListActivityViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ProductListActivityViewModel.class);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
