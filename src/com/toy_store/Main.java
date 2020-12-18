package com.toy_store;

import com.toy_store.marketing.Store;
import com.toy_store.production.DuplicateManufacturerException;
import com.toy_store.production.DuplicateProductException;
import com.toy_store.production.Product;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Store store = Store.getInstance();
        Product[] products = store.readCSV("my_amazon.csv");
        for (Product product : products) {
            try {
                store.addProduct(product);
                System.out.println(product);
                store.addManufacturer(product.getManufacturer());
            } catch (DuplicateProductException | DuplicateManufacturerException e) {
                System.out.println(e.getMessage());
            }
        }
        store.saveStore("test.data");

        System.out.println("\n\n\n");

        store = Store.loadStore("test.data");
        for (int i = 0; i < store.productsNum; i++) {
            System.out.println(store.products[i]);
        }
    }
}
