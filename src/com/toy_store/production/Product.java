package com.toy_store.production;

import com.toy_store.financial.Discount;

public class Product {
    private String uniqueId;
    private String name;
    private Manufacturer manufacturer;
    private double price;
    private int quantity;
    private Discount discount;

    public Product setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        manufacturer.incCount();
        return this;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product setDiscount(Discount discount) {
        this.discount = discount;
        return this;
    }

    public Discount getDiscount() {
        return discount;
    }
}
