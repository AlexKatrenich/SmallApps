package com.katrenich.alex.klara.vacancyScreen.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

public class VacancyListViewModel extends AndroidViewModel {
    public static final String TAG="VacancyListViewModel";
    public ObservableInt loading;

    public VacancyListViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        loading = new ObservableInt(View.GONE);
    }
}
