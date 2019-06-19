package com.katrenich.alex.klara.assortmentScreen.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.assortmentScreen.adapter.ProductListAdapter;
import com.katrenich.alex.klara.model.CakeProduct;
import com.katrenich.alex.klara.model.DrinkProduct;
import com.katrenich.alex.klara.model.PattyProduct;
import com.katrenich.alex.klara.model.Product;
import com.katrenich.alex.klara.model.Products;
import com.katrenich.alex.klara.model.SaladProduct;

import java.util.ArrayList;
import java.util.List;


public class ProductListViewModel extends ViewModel {
    private static final String TAG = "ProductListViewModel";
    public ObservableInt loading;
    private ProductListAdapter mAdapter;
    private Products mProducts;

    public void init(){
        loading = new ObservableInt(View.GONE);
        mAdapter = new ProductListAdapter(R.layout.product_list_item, this);
        mProducts = new Products();
    }

    public ProductListAdapter getAdapter(){
        return mAdapter;
    }

    public void fetchList(){
        mProducts.fetchList();
    }

    public MutableLiveData<List<Product>> getProducts(){
        return mProducts.getProducts();
    }

    public void setProductsInAdapter(List<Product> products){
        this.mAdapter.setProducts(products);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onItemClick(Integer index){
        Log.i(TAG, "onItemClick: " + index);
    }

    public Product getProductAt(Integer position){
        Log.i(TAG, "getProductAt position:  " + mProducts.getProducts().getValue().get(position));
        if (mProducts.getProducts().getValue() != null &&
                mProducts.getProducts().getValue().size() > position){

            Log.i(TAG, "getProductAt position:  " + mProducts.getProducts().getValue().get(position));
            return mProducts.getProducts().getValue().get(position);
        }
        return null;
    }

    public void showAllProducts() {
        Log.i(TAG, "showAllProducts: ");
        mProducts.setCurrentListProducts(mProducts.getAllProducts());
    }

    public void showCakeProducts() {
        Log.i(TAG, "showCakeProducts: ");

        List<Product> cakeProducts = new ArrayList<>();
        List<Product> products = mProducts.getAllProducts();
        for (Product p: products) {
            if (p.getClass() == CakeProduct.class){
                cakeProducts.add(p);
            }
        }
        mProducts.setCurrentListProducts(cakeProducts);
    }

    public void showDrinkProducts() {
        Log.i(TAG, "showDrinkProducts: ");
        List<Product> drinkProducts = new ArrayList<>();
        List<Product> products = mProducts.getAllProducts();
        for (Product p: products) {
            if (p.getClass() == DrinkProduct.class){
                drinkProducts.add(p);
            }
        }
        mProducts.setCurrentListProducts(drinkProducts);
    }

    public void showPattyProducts() {
        Log.i(TAG, "showPattyProducts: ");
        List<Product> pattyProducts = new ArrayList<>();
        List<Product> products = mProducts.getAllProducts();
        for (Product p: products) {
            if (p.getClass() == PattyProduct.class){
                pattyProducts.add(p);
            }
        }
        mProducts.setCurrentListProducts(pattyProducts);
    }

    public void showSaladProducts() {
        Log.i(TAG, "showSaladProducts: ");

        List<Product> saladProducts = new ArrayList<>();
        List<Product> products = mProducts.getAllProducts();
        for (Product p: products) {
            if (p.getClass() == SaladProduct.class){
                saladProducts.add(p);
            }
        }
        mProducts.setCurrentListProducts(saladProducts);
    }
}
