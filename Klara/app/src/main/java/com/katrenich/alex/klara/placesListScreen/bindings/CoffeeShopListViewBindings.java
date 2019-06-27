package com.katrenich.alex.klara.placesListScreen.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CoffeeShopListViewBindings {
    @BindingAdapter("setCoffeeShopListAdapter")
    public static void bindCoffeeShopListAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("setImageByUrl")
    public static void bindCoffeeShopListAdapter(ImageView imageView, String imageUrl){
        if (imageUrl != null){
            Glide.with(imageView).load(imageUrl).into(imageView);
        }
    }
}
