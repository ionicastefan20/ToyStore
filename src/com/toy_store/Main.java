package com.toy_store;

import com.toy_store.marketing.Store;
import com.toy_store.production.Product;

public class Main {
    public static void main(String[] args) {
        Product[] products = Store.getInstance().readCSV("amazon_co-ecommerce_sample.csv");
        System.out.println();
    }
}
