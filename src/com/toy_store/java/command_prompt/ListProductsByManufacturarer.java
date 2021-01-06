package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.Manufacturer;
import com.toy_store.java.production.Product;

public class ListProductsByManufacturarer implements Command {

    private final String manufacturerName;

    public ListProductsByManufacturarer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public void execute() {
        for (Product product : Store.getInstance().getProductsByManufacturer(
                new Manufacturer(manufacturerName))) {
            System.out.println(product);
        }
    }
}
