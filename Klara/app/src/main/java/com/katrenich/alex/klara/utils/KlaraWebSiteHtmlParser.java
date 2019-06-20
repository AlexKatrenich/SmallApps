package com.katrenich.alex.klara.utils;

import android.support.annotation.Nullable;
import android.util.Log;

import com.katrenich.alex.klara.model.CakeProduct;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class KlaraWebSiteHtmlParser {

    private static final String TAG = "KlaraWebSiteHtmlParser";

    public static List<CakeProduct> parseListCakeProducts(Document doc) {
        List<CakeProduct> products = new ArrayList<>();
        if (doc != null){
            int size = doc.select("li[class=catalog__assortment-item]").size();
            for (int i = 0; i < size ; i++) {
                Elements productName = doc.select("div[class=catalog__assortment-name]").eq(i);
                String name = productName.text();

                Elements productWeight = doc.select("div[class=catalog__assortment-weight]").eq(i);
                Integer weight = getNumberFromText(productWeight.text());

                Elements productPrice = doc.select("div[class=catalog__assortment-price]").eq(i);
                Integer price = getNumberFromText(productPrice.text());

                CakeProduct cakeProduct = new CakeProduct(name, weight, price);
                products.add(cakeProduct);
            }
        }

        return products;
    }

    public static Integer getNumberFromText(String text) throws NumberFormatException{
        if (text != null && !text.isEmpty()){
            text = text.replaceAll("[^0-9]", "");
            try {
                Integer i = Integer.valueOf(text);
                return i;
            } catch (Exception e){
                throw new NumberFormatException();
            }
        } else {
            throw new NumberFormatException();
        }

    }
}
