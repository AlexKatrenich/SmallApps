package com.katrenich.alex.klara;

import android.databinding.ObservableInt;

import com.katrenich.alex.klara.model.CakeProduct;
import com.katrenich.alex.klara.net.NetworkService;
import com.katrenich.alex.klara.utils.KlaraWebSiteHtmlParser;

import org.jsoup.nodes.Document;
import org.junit.Test;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    public void testGetNumberFromText(){
        String[] s = {"15 г", "15 грн", "//15 грн'", "'15 '", "15 ", "15"};
        try {
            for (int i = 0; i < s.length; i++) {
                Integer j = KlaraWebSiteHtmlParser.getNumberFromText(s[i]);
                if (j != null) {
                    System.out.println(j);
                    assertEquals(j.intValue(), 15);
                } else {
                    System.err.println("integer == null");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}