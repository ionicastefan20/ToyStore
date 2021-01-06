package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.Product;

public class ListProducts implements Command {

    @Override
    public void execute() {
        for (Product product : Store.getInstance().getProducts()) {
            System.out.println(product);
        }
    }
}
