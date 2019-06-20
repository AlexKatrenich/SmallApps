package com.katrenich.alex.klara.model;

public class SaladProduct extends Product {
    public SaladProduct(){}

    public SaladProduct(String name) {
        super(name);
    }

    public SaladProduct(String name, Integer weight, Integer price) {
        super(name, weight, price);
    }
}
