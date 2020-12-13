package com.toy_store.production;

import com.toy_store.financial.Discount;

public class Product {
    private String uniqueId;
    private String name;
    private Manufacturer manufacturer;
    private double price;
    private int quantity;
    private Discount discount;

    public Product() {}

    public Discount getDiscount() {
        return discount;
    }
}
