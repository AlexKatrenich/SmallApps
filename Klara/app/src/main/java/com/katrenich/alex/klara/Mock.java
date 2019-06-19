package com.katrenich.alex.klara;

import com.katrenich.alex.klara.model.CakeProduct;
import com.katrenich.alex.klara.model.DrinkProduct;
import com.katrenich.alex.klara.model.PattyProduct;
import com.katrenich.alex.klara.model.Product;
import com.katrenich.alex.klara.model.SaladProduct;

import java.util.ArrayList;
import java.util.List;

public class Mock {

    public static List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new PattyProduct("Пиріжок з вишнею", 90, 1900));
        products.add(new PattyProduct("Пиріжок з маком", 90, 1500));
        products.add(new PattyProduct("Ватрушка", 90, 1400));
        products.add(new PattyProduct("Пиріжок з повидлом", 90, 1700));
        products.add(new PattyProduct("Пиріжок з персиком", 90, 1500));
        products.add(new PattyProduct("Пиріжок з яблуком та корицею", 90, 2200));
        products.add(new PattyProduct("Пиріжок зі смородиною", 90, 1400));
        products.add(new PattyProduct("Плюшка з корицею", 90, 2100));
        products.add(new PattyProduct("Плюшка із заварним кремом", 90, 2400));
        products.add(new PattyProduct("Бублик з маком та кунжутом", 90, 2500));
        products.add(new PattyProduct("Ватрушка з маком", 90, 1700));
        products.add(new PattyProduct("Пиріжок з рисом та яйцем", 90, 1700));
        products.add(new PattyProduct("Пиріжок з яйцем та цибулею", 90, 1800));
        products.add(new PattyProduct("Пиріжок з рибою", 90, 1900));
        products.add(new PattyProduct("Пиріжок з грибами", 90, 2400));
        products.add(new PattyProduct("Пиріжок з горохом", 90, 1700));
        products.add(new PattyProduct("Пиріжок з бараниною", 90, 1600));
        products.add(new PattyProduct("Пиріжок з куркою та грибами", 90, 1600));
        products.add(new PattyProduct("Пиріжок зі шпинатом та сиром", 90, 1400));
        products.add(new PattyProduct("Пиріжок сосиска в тісті", 120, 1600));
        products.add(new CakeProduct("Пиріг з вишнею", 1000, 32000));
        products.add(new CakeProduct("Пиріг з маком", 1000, 25000));
        products.add(new CakeProduct("Пиріг із солодким сиром", 1000, 25000));
        products.add(new CakeProduct("Пиріг з персиком", 1000, 16000));
        products.add(new DrinkProduct("Компот", 300, 2500));
        products.add(new DrinkProduct("Узвар", 500, 2300));
        products.add(new DrinkProduct("Імбирний чай", 500, 2900));
        products.add(new DrinkProduct("Лимонад", 500, 3300));
        products.add(new DrinkProduct("Чай в асортименті \"Грінфілд\"", 500, 1600));
        products.add(new DrinkProduct("Чай листовий \"Світ чаю\"", 500, 2400));
        products.add(new DrinkProduct("Чай Журавлина", 500, 2600));
        products.add(new DrinkProduct("Чай обліпиховий", 300, 2700));
        products.add(new SaladProduct("Олів'є", 300, 4300));
        products.add(new SaladProduct("Дністер", 200, 4300));
        products.add(new SaladProduct("Грецький", 200, 5600));
        products.add(new SaladProduct("Вінегрет", 200, 3400));
        return products;
    }
}
