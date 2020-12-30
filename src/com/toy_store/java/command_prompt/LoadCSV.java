package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.DuplicateProductException;
import com.toy_store.java.production.Product;

public class LoadCSV implements Command {

    private final Store store = Store.getInstance();
    private final String filename;

    public LoadCSV(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute() {
        Product[] products = store.readCSV(filename);
        for (Product product : products) {
            try {
                store.addProduct(product);
            } catch (DuplicateProductException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
