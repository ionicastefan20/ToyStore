package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Discount;
import com.toy_store.java.marketing.Store;

public class ListDiscounts implements Command {

    @Override
    public void execute() {
        for (Discount discount : Store.getInstance().getDiscounts()) {
            System.out.println(discount);
        }
    }
}
