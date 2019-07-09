package com.katrenich.alex.klara.vacancyScreen.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class VacancyListViewBinding {
    @BindingAdapter("setVacancyListAdapter")
    public static void bindVacancyListAdapter(RecyclerView rv, RecyclerView.Adapter<?> adapter){
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(adapter);
    }
}
