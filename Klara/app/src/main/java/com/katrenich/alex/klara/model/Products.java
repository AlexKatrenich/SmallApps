package com.katrenich.alex.klara.model;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;

import com.katrenich.alex.klara.Mock;

import java.util.List;

public class Products extends BaseObservable {
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        return products;
    }

    public void fetchList(){
        products.setValue(Mock.getProductList());
    }
}
