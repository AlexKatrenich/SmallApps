package com.katrenich.alex.klara;

import android.databinding.ObservableInt;

import com.katrenich.alex.klara.model.CakeProduct;
import com.katrenich.alex.klara.model.Product;
import com.katrenich.alex.klara.net.NetworkService;
import com.katrenich.alex.klara.utils.KlaraWebSiteHtmlParser;

import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testKlaraWebSiteQueries(){
        Single<Document> document = NetworkService.getInstance().getKlaraWebSiteInfo().getProductsCatalog();
        checkQueriesResult(document, "Catalog all products");
        document = NetworkService.getInstance().getKlaraWebSiteInfo().getCakeProductsCatalog();
        checkQueriesResult(document, "Catalog Cakes");
        document = NetworkService.getInstance().getKlaraWebSiteInfo().getDrinkProductsCatalog();
        checkQueriesResult(document, "Catalog Drinks");
        document = NetworkService.getInstance().getKlaraWebSiteInfo().getPattyProductsCatalog();
        checkQueriesResult(document, "Catalog Patties");
        document = NetworkService.getInstance().getKlaraWebSiteInfo().getSaladProductsCatalog();
        checkQueriesResult(document, "Catalog salads");
    }

    private void checkQueriesResult(Single<Document> doc, String message){
        doc.subscribe(document -> System.out.println(message + " DONE")
                , throwable -> new Exception(message + " FAILED"));
    }

    @Test
    public void getAllProducts(){
        Single<Document> documentCakes = NetworkService.getInstance().getKlaraWebSiteInfo().getCakeProductsCatalog();
        Single<Document> documentDrinks = NetworkService.getInstance().getKlaraWebSiteInfo().getDrinkProductsCatalog();
        Single<Document> documentPatty = NetworkService.getInstance().getKlaraWebSiteInfo().getPattyProductsCatalog();
        Single<Document> documentSalad = NetworkService.getInstance().getKlaraWebSiteInfo().getSaladProductsCatalog();
        System.out.println("All info getting");
        Single<List<Product>> listCakes = documentCakes.map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.CAKES));
        Single<List<Product>> listDrinks = documentDrinks.map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.DRINKS));
        Single<List<Product>> listPatties = documentPatty.map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.PATTIES));
        Single<List<Product>> listSalad = documentSalad.map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.SALADS));
        System.out.println("Documents transformed to Lists");

        listCakes.zipWith(listDrinks, (products, products2) -> {
            products.addAll(products2);
            return products;
        }).zipWith(listPatties, (products, products2) -> {
            products.addAll(products2);
            return products;
        }).zipWith(listSalad, (products, products2) -> {
            products.addAll(products2);
            return products;
        }).subscribe(products -> {
            for (Product p : products) {
                System.out.println(p);
            }
        }, Throwable::printStackTrace);


    }

    @Test
    public void getOneProduct(){
        NetworkService.getInstance()
                .getKlaraWebSiteInfo()
                .getCakeProductsCatalog()
                .map(document -> KlaraWebSiteHtmlParser.parseListProducts(document, KlaraWebSiteHtmlParser.ProductType.CAKES))
                .subscribe(products -> {
                    for (Product p : products) {
                        System.out.println(p);
                    }
                }, Throwable::printStackTrace);
    }
}