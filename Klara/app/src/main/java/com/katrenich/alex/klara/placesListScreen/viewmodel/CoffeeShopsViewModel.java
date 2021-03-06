package com.katrenich.alex.klara.placesListScreen.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.katrenich.alex.klara.App;
import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.net.NetworkService;
import com.katrenich.alex.klara.placesListScreen.adapter.CoffeeShopsListAdapter;
import com.katrenich.alex.klara.placesListScreen.model.CoffeeShop;
import com.katrenich.alex.klara.utils.KlaraWebSiteHtmlParser;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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
        if (shopList != null && shopList.getValue().size() > position){
            return shopList.getValue().get(position);
        }
            return null;
    }

    public void onItemClick(Integer index){
        CoffeeShop shop = shopList.getValue().get(index);
        Log.i(TAG, "onItemClick: CoffeeShop - " + shop.toString());
        Uri intentUri = Uri.parse("geo:0,0?q=" + shop.getAddress());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            if (mapIntent.resolveActivity(App.getInstance().getPackageManager()) != null){
                App.getInstance().startActivity(mapIntent);
            }
        } catch (NullPointerException e){
            Log.e(TAG, "onItemClick: NullPointerException: Couldn`t open map." + e.getMessage());
            Toast.makeText(App.getInstance().getBaseContext(), "Couldn`t open map", Toast.LENGTH_SHORT).show();
        }
    }
}
