package com.katrenich.alex.klara.model;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;

import java.util.ArrayList;
import java.util.List;




public class Products extends BaseObservable {
    private static final String TAG = "Products";
    private MutableLiveData<List<Product>> currentProducts = new MutableLiveData<>();
    private List<Product> allProducts = new ArrayList<>();

    public MutableLiveData<List<Product>> getCurrentProducts() {
        return currentProducts;
    }

    public void setCurrentListProducts(List<Product> products){
        this.currentProducts.setValue(products);
    }

    public List<Product> getAllProducts(){
        return allProducts;
    }

    public void setAllProducts(List<? extends Product> products){
        allProducts.clear();
        allProducts.addAll(products);
    }

    public void addToAllProducts(List<? extends Product> products){
        allProducts.addAll(products);
    }

}
