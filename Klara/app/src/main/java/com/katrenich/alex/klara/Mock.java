package com.katrenich.alex.klara;

import com.katrenich.alex.klara.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Mock {

    public static List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Пиріжок з вишнею", 90, 1900));
        products.add(new Product("Пиріжок з маком", 90, 1500));
        products.add(new Product("Ватрушка", 90, 1400));
        products.add(new Product("Пиріжок з повидлом", 90, 1700));
        products.add(new Product("Пиріжок з персиком", 90, 1500));
        products.add(new Product("Пиріжок з яблуком та корицею", 90, 2200));
        products.add(new Product("Пиріжок зі смородиною", 90, 1400));
        products.add(new Product("Плюшка з корицею", 90, 2100));
        products.add(new Product("Плюшка із заварним кремом", 90, 2400));
        products.add(new Product("Бублик з маком та кунжутом", 90, 2500));
        products.add(new Product("Ватрушка з маком", 90, 1700));
        products.add(new Product("Пиріжок з рисом та яйцем", 90, 1700));
        products.add(new Product("Пиріжок з яйцем та цибулею", 90, 1800));
        products.add(new Product("Пиріжок з рибою", 90, 1900));
        products.add(new Product("Пиріжок з грибами", 90, 2400));
        products.add(new Product("Пиріжок з горохом", 90, 1700));
        products.add(new Product("Пиріжок з бараниною", 90, 1600));
        products.add(new Product("Пиріжок з куркою та грибами", 90, 1600));
        products.add(new Product("Пиріжок зі шпинатом та сиром", 90, 1400));
        products.add(new Product("Пиріжок сосиска в тісті", 120, 1600));
        products.add(new Product("Пиріг з вишнею", 1000, 32000));
        products.add(new Product("Пиріг з маком", 1000, 25000));
        products.add(new Product("Пиріг із солодким сиром", 1000, 25000));
        products.add(new Product("Пиріг з персиком", 1000, 16000));

        return products;
    }
}
