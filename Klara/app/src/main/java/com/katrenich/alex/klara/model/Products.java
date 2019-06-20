package com.katrenich.alex.klara.model;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.util.Log;

import com.katrenich.alex.klara.net.NetworkService;
import com.katrenich.alex.klara.utils.KlaraWebSiteHtmlParser;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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

    public void fetchList(){
        Log.i(TAG, "fetchList: ");
        fetchCakeProductList();
    }

    private void fetchCakeProductList(){
        Log.i(TAG, "fetchCakeProductList: ");
        Single<Document> document = NetworkService.getInstance().getKlaraWebSiteInfo().getCakeProductsCatalog();
        document.subscribeOn(Schedulers.io())
                .map(KlaraWebSiteHtmlParser::parseListCakeProducts)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cakeProducts -> {
                            for (CakeProduct cp : cakeProducts) {
                                allProducts.add(cp);
                                Log.i(TAG, "fetchCakeProductList: 1" + cp);
                            }
                            setCurrentListProducts(allProducts);
                }
                , throwable -> {
                            Log.e(TAG, "fetchCakeProductList: ", throwable);
                        });
    }
}
