package com.toy_store.marketing;

import com.toy_store.financial.*;
import com.toy_store.production.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

public class Store {
//    private final int DEFAULT_ARRAY_SIZE = 8;
    private static final String DEFAULT_STORE_NAME = "POO_Store";
    private static Store instance = null;
    private String name;
    private Currency currency;
    private List<Product> products;
    private List<Manufacturer> manufacturers;


    public static Store getInstance() {
        if (instance == null) {
            instance = new Store(DEFAULT_STORE_NAME, new Currency());
        }
        return instance;
    }

    private Store(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
        this.products = new ArrayList<>();
        this.manufacturers = new ArrayList<>();
        this.instance = this;
    }

    List<Product> readCSV(String filename) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("amazon_co-ecommerce_sample.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return this.products;
    }

    void addProduct(Product product) throws DuplicateProductException {

    }

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
