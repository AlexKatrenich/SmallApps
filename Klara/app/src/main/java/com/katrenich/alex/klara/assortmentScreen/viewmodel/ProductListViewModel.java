package com.katrenich.alex.klara.assortmentScreen.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.assortmentScreen.adapter.ProductListAdapter;
import com.katrenich.alex.klara.model.Product;
import com.katrenich.alex.klara.model.Products;

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
                position != null &&
                mProducts.getProducts().getValue().size() > position){

            Log.i(TAG, "getProductAt position:  " + mProducts.getProducts().getValue().get(position));
            return mProducts.getProducts().getValue().get(position);
        }
        return null;
    }
}
