package com.katrenich.alex.klara.placesListScreen.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.placesListScreen.adapter.CoffeeShopsListAdapter;

public class CoffeeShopsViewModel extends AndroidViewModel {
    public ObservableInt loading;
    private CoffeeShopsListAdapter mAdapter;

    public CoffeeShopsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        loading = new ObservableInt(View.GONE);
        mAdapter = new CoffeeShopsListAdapter(R.layout.item_list_coffee_shop, this);
    }

    public CoffeeShopsListAdapter getAdapter(){
        return mAdapter;
    }

}
