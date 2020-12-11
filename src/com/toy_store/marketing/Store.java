package com.toy_store.marketing;

import com.toy_store.financial.*;
import com.toy_store.production.*;

import java.io.IOException;

public class Store {
//    private final int DEFAULT_ARRAY_SIZE = 8;
    private static final String DEFAULT_STORE_NAME = "POO_Store";
    private static Store instance = null;
    private String name;
    private Currency currency;
    private Product[] products;
    private Manufacturer[] manufacturers;


    public static Store getInstance() {
        if (instance == null) {
            instance = new Store(DEFAULT_STORE_NAME, new Currency());
        }
        return instance;
    }

    private Store(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
        this.products = new Product[1];
        this.manufacturers = new Manufacturer[1];
        this.instance = this;
    }

    Product[] readCSV(String filename) {
        return new Product[1];
    }

    void addProduct(Product product) throws DuplicateProductException {}

    void addManufacturer(Manufacturer manufacturer) throws DuplicateManufacturerException {}

    void loadStore(String filename) throws IOException {}

    void saveStore(String filename) throws IOException {}

    void changeCurrency(Currency currency) throws CurrencyNotFoundException {}

    void applyDiscount(Discount discount) throws DiscountNotFoundException, NegativePriceException {}

    Product[] getProductsByManufacturer(Manufacturer manufacturer) {
        return new Product[1];
    }

    double calculateTotal(Product[] product) {
        return 1.0;
    }
}
