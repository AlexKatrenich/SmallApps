package com.katrenich.alex.klara.utils;
import com.katrenich.alex.klara.model.CakeProduct;
import com.katrenich.alex.klara.model.DrinkProduct;
import com.katrenich.alex.klara.model.PattyProduct;
import com.katrenich.alex.klara.model.Product;
import com.katrenich.alex.klara.model.SaladProduct;
import com.katrenich.alex.klara.placesListScreen.model.CoffeeShop;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class KlaraWebSiteHtmlParser {
    private static final String TAG = "KlaraWebSiteHtmlParser";

    public enum ProductType{
        CAKES,
        DRINKS,
        PATTIES,
        SALADS
    }

    // метод парсить з html-документу список продуктів(десерти/торти)
    public static List<Product> parseListProducts(Document doc, ProductType type) {
        List<Product> products = new ArrayList<>();
        if (doc != null){
            int size = doc.select("li[class=catalog__assortment-item]").size();
            for (int i = 0; i < size ; i++) {
                Elements productName = doc.select("div[class=catalog__assortment-name]").eq(i);
                String name = productName.text();

                Elements productWeight = doc.select("div[class=catalog__assortment-weight]").eq(i);
                Integer weight;

                Elements productPrice = doc.select("div[class=catalog__assortment-price]").eq(i);
                Integer price;

               try {
                   weight = getNumberFromText(productWeight.text());
                   price = getNumberFromText(productPrice.text());
               } catch (NumberFormatException e){
                   continue;
               }

                Product product = null;
                switch (type) {
                    case CAKES: {
                        product = new CakeProduct();
                        break;
                    }
                    case DRINKS: {
                        product = new DrinkProduct();
                        break;
                    }
                    case SALADS: {
                        product = new SaladProduct();
                        break;
                    }
                    case PATTIES: {
                        product = new PattyProduct();
                        break;
                    }
                }

                product.setName(name);
                product.setWeight(weight);
                product.setPrice(price);

                products.add(product);
            }
        }

        return products;
    }

    // метод видаляє всі зайві символи, окрім чисел та повертає числове значення
    protected static Integer getNumberFromText(String text) throws NumberFormatException{
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

    // метод для парсингу з HTML-документу списку кав'ярень
    public static List<CoffeeShop> parseListCoffeeShops(Document doc){
        List<CoffeeShop> shops = new ArrayList<>();
        if (doc != null){
            int size = doc.select("li[class=contacts__slider-item]").size();
            for (int i = 0; i < size; i++) {
                Elements shopImageURL = doc.select("img[class=contacts__slider-img]").eq(i);
                String imageURL = shopImageURL.attr("src");

                Elements shopAddress = doc.select("div[class=contacts__slider-address]").eq(i);
                String address = shopAddress.text();

                Elements shopWorkTime = doc.select("div[class=contacts__slider-address-operating]").eq(i);
                String time = shopWorkTime.text();

                CoffeeShop shop = new CoffeeShop(address, time, imageURL);
                shops.add(shop);
            }
        }
        return shops;
    }
}
