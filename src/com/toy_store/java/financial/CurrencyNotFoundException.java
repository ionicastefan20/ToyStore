package com.toy_store.java.financial;

public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException() {
        super("The given currency does not exists");
    }
}
