package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.DiscountType;
import com.toy_store.java.marketing.Store;

public class AddDiscount implements Command {

    private final DiscountType discountType;
    private final double value;
    private final String name;

    public AddDiscount(String discountTypeStr, double value, String name) {
        this.discountType = DiscountType.valueOf(discountTypeStr + "_DISCOUNT");
        this.value = value;
        this.name = name;
    }

    @Override
    public void execute() {
        Store.getInstance().createDiscount(name, discountType, value);
    }
}
