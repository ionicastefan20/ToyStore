package com.toy_store.java.marketing;

public class DiscountNotFoundException extends Exception {
    public DiscountNotFoundException() {
        super("The given discount does not exist");
    }
}
