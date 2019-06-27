package com.katrenich.alex.klara.placesListScreen.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.katrenich.alex.klara.Mock;
import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.net.NetworkService;
import com.katrenich.alex.klara.placesListScreen.adapter.CoffeeShopsListAdapter;
import com.katrenich.alex.klara.placesListScreen.model.CoffeeShop;
import com.katrenich.alex.klara.utils.KlaraWebSiteHtmlParser;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class CoffeeShopsViewModel extends AndroidViewModel {
    public static final String TAG = "CoffeeShopsViewModel";
    public ObservableInt loading;
    private CoffeeShopsListAdapter mAdapter;
    private boolean dataWasLoad;
    public MutableLiveData<List<CoffeeShop>> shopList;

    public CoffeeShopsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        loading = new ObservableInt(View.GONE);
        mAdapter = new CoffeeShopsListAdapter(R.layout.item_list_coffee_shop, this);
        shopList = new MutableLiveData<>();
        dataWasLoad = false;
    }

    public CoffeeShopsListAdapter getAdapter(){
        return mAdapter;
    }

    public void fetchList() {
        if (!dataWasLoad){
            loadData();
        }
    }

    private void loadData() {
//        List<CoffeeShop> shops = Mock.getCoffeShopsList();
//        shopList.setValue(shops);
        NetworkService.getInstance()
                .getKlaraWebSiteInfo()
                .getCoffeeShopsCatalog()
                .subscribeOn(Schedulers.io())
                .map(KlaraWebSiteHtmlParser::parseListCoffeeShops)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coffeeShops -> {
                    dataWasLoad = true;
                    shopList.setValue(coffeeShops);
                    }, Throwable::printStackTrace);
    }

    public void setCoffeeShopsInAdapter(List<CoffeeShop> shops) {
        this.mAdapter.setCoffeeShops(shops);
        this.mAdapter.notifyDataSetChanged();
    }

    public CoffeeShop getCoffeeShopAt(Integer position){
        Log.i(TAG, "getCoffeeShopAt: POSITION: " + position);
        if (shopList != null && shopList.getValue().size() > position){
            Log.i(TAG, "getCoffeeShopAt: " + shopList.getValue().get(position));
            return shopList.getValue().get(position);
        }
            return null;
    }
}
