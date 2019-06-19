package com.katrenich.alex.klara.model;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;

import com.katrenich.alex.klara.Mock;

import java.util.ArrayList;
import java.util.List;

public class Products extends BaseObservable {
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private List<Product> allProducts = new ArrayList<>();

    public MutableLiveData<List<Product>> getProducts() {
        return products;
    }

    public void setCurrentListProducts(List<Product> products){
        this.products.setValue(products);
    }

    public List<Product> getAllProducts(){
        return allProducts;
    }

    public void fetchList(){
        products.setValue(Mock.getProductList());
        allProducts.addAll(Mock.getProductList());
    }
}
