package com.toy_store.financial;

public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException() {
        super("The given currency does not exists");
    }
}
