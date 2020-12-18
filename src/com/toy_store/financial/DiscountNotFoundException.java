package com.toy_store.financial;

public class DiscountNotFoundException extends Exception {
    public DiscountNotFoundException() {
        super("The given discount does not exist");
    }
}
