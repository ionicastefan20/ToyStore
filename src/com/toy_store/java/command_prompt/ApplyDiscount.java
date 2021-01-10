package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.NegativePriceException;
import com.toy_store.java.marketing.DiscountNotFoundException;
import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

public class ApplyDiscount implements Command {

    private final String name;

    public ApplyDiscount(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        try {
            Store.getInstance().applyDiscount(name);
        } catch (DiscountNotFoundException | NegativePriceException e) {
            out.println(e.getMessage());
        }
    }
}
