package com.katrenich.alex.klara.assortmentScreen.model;

public class DrinkProduct extends Product {

    public DrinkProduct(){}

    public DrinkProduct(String name) {
        super(name);
    }

    public DrinkProduct(String name, Integer weight, Integer price) {
        super(name, weight, price);
    }
}
