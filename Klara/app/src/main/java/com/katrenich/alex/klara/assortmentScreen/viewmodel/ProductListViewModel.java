package com.katrenich.alex.klara.assortmentScreen.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.katrenich.alex.klara.App;
import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.assortmentScreen.adapter.ProductListAdapter;
import com.katrenich.alex.klara.assortmentScreen.model.CakeProduct;
import com.katrenich.alex.klara.assortmentScreen.model.DrinkProduct;
import com.katrenich.alex.klara.assortmentScreen.model.PattyProduct;
import com.katrenich.alex.klara.assortmentScreen.model.Product;
import com.katrenich.alex.klara.assortmentScreen.model.Products;
import com.katrenich.alex.klara.assortmentScreen.model.SaladProduct;
import com.katrenich.alex.klara.net.NetworkService;
import com.katrenich.alex.klara.utils.KlaraWebSiteHtmlParser;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ProductListViewModel extends AndroidViewModel{
    private static final String TAG = "ProductListViewModel";
    public ObservableInt loading;
    private ProductListAdapter mAdapter;
    private Products mProducts;
    public MutableLiveData<Boolean> dataWasLoaded;
    private Integer bnvState;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        loading = new ObservableInt(View.GONE);
        mAdapter = new ProductListAdapter(R.layout.item_list_product, this);
        mProducts = new Products();
        dataWasLoaded = new MutableLiveData<>();
        dataWasLoaded.setValue(false);
        bnvState = null;
    }

    public ProductListAdapter getAdapter(){
        return mAdapter;
    }

    public void fetchList(){
        if (dataWasLoaded.getValue()) {
            if(bnvState == null){
                List<Product> productList = mProducts.getAllProducts();
                Log.i(TAG, "fetchList: " + productList);
                mProducts.setCurrentListProducts(productList);
            }
        } else {
            loadData();
        }
    }

    public void saveBnvState(int i){
        bnvState = i;
    }

    public Integer restoreBnvState(){
        return bnvState;
    }

    public MutableLiveData<List<Product>> getProducts(){
        return mProducts.getCurrentProducts();
    }

    public void setProductsInAdapter(List<Product> products){
        this.mAdapter.setProducts(products);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onItemClick(Integer index){
        Log.i(TAG, "onItemClick: " + index);
    }

    public Product getProductAt(Integer position){
        if (mProducts.getCurrentProducts().getValue() != null &&
                mProducts.getCurrentProducts().getValue().size() > position){

            return mProducts.getCurrentProducts().getValue().get(position);
        }
        return null;
    }

    public void showAllProducts() {
        mProducts.setCurrentListProducts(mProducts.getAllProducts());
    }

    public void showCakeProducts() {
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
        List<Product> saladProducts = new ArrayList<>();
        List<Product> products = mProducts.getAllProducts();
        for (Product p: products) {
            if (p.getClass() == SaladProduct.class){
                saladProducts.add(p);
            }
        }
        mProducts.setCurrentListProducts(saladProducts);
    }

    private void loadData(){
        // отримання Single-об'єктів з сайту
        Single<Document> documentCakes = NetworkService.getInstance().getKlaraWebSiteInfo().getCakeProductsCatalog();
        Single<Document> documentDrinks = NetworkService.getInstance().getKlaraWebSiteInfo().getDrinkProductsCatalog();
        Single<Document> documentPatty = NetworkService.getInstance().getKlaraWebSiteInfo().getPattyProductsCatalog();
        Single<Document> documentSalad = NetworkService.getInstance().getKlaraWebSiteInfo().getSaladProductsCatalog();

        // перетворення об'єктів в потоки зі списками
        Single<List<Product>> listCakes = documentCakes.subscribeOn(Schedulers.io()).map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.CAKES));
        Single<List<Product>> listDrinks = documentDrinks.subscribeOn(Schedulers.io()).map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.DRINKS));
        Single<List<Product>> listPatties = documentPatty.subscribeOn(Schedulers.io()).map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.PATTIES));
        Single<List<Product>> listSalad = documentSalad.subscribeOn(Schedulers.io()).map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.SALADS));

        listCakes.zipWith(listDrinks, (products, products2) -> {
            products.addAll(products2);
            return products;
        }).zipWith(listPatties, (products, products2) -> {
            products.addAll(products2);
            return products;
        }).zipWith(listSalad, (products, products2) -> {
            products.addAll(products2);
            return products;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(products -> {
            dataWasLoaded.setValue(true);
            mProducts.addToAllProducts(products);
        }, Throwable::printStackTrace);
    }

    public String getStringWeight(Product product){
        if (product != null) {
            if (product.getClass() == DrinkProduct.class) {
                return product.getWeight() + " мл";
            } else {
                return product.getWeight() + " г";
            }
        } else {
            return " ";
        }
    }

    public String getStringPrice(Product product){
        if (product != null) {
            return product.getPrice() + " грн";
        } else {
            return " ";
        }
    }

    @BindingAdapter({"app:product"})
    public static void loadImage(ImageView view, Product product){
        int drawableID;
        if (product.getClass() == DrinkProduct.class){
            drawableID = R.drawable.ic_coffee;
        } else if (product.getClass() == CakeProduct.class){
            drawableID = R.drawable.ic_cake;
        } else if (product.getClass() == SaladProduct.class){
            drawableID = R.drawable.ic_salad;
        } else {
            drawableID = R.drawable.ic_patty;
        }

        Glide.with(App.getInstance()).load(App.getInstance().getDrawable(drawableID)).into(view);
    }
}
