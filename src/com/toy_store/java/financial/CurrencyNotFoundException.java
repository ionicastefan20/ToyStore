package com.toy_store.java.financial;

public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException(String symbol) {
        super("Currency (" + symbol + ") does not exist.");
    }
}
