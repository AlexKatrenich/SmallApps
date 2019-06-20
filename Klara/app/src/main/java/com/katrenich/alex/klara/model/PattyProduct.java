package com.katrenich.alex.klara.model;

public class PattyProduct extends Product {

    public PattyProduct(){}

    public PattyProduct(String name) {
        super(name);
    }

    public PattyProduct(String name, Integer weight, Integer price) {
        super(name, weight, price);
    }
}
