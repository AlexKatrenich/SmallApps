package com.katrenich.alex.klara.vacancyScreen.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.databinding.ActivityVacancyListBinding;
import com.katrenich.alex.klara.vacancyScreen.viewmodel.VacancyListViewModel;

public class VacancyListActivity extends AppCompatActivity {
    public static final String TAG = "VacancyListActivity";
    private Toolbar mToolbar;
    private VacancyListViewModel mViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vacancy_list);
            init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(VacancyListViewModel.class);
        ActivityVacancyListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_vacancy_list);
        if (savedInstanceState == null){
            mViewModel.init();
        }

        binding.setVacancyListViewModel(mViewModel);

        mToolbar = findViewById(R.id.tb_vacancy_list_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
