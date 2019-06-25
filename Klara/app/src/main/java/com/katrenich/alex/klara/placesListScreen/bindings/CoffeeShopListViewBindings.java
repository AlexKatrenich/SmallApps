package com.katrenich.alex.klara.placesListScreen.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CoffeeShopListViewBindings {
    @BindingAdapter("setCoffeeShopListAdapter")
    public static void bindCoffeeShopListAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
