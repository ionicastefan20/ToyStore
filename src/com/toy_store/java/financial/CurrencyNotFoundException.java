package com.toy_store.java.financial;

/**
 * Exception thrown when a currency cannot be found on the store.
 */
public class CurrencyNotFoundException extends Exception {

    /**
     * Constructor for the the CurrencyNotFoundException.
     * @param symbol the symbol of the currency
     */
    public CurrencyNotFoundException(String symbol) {
        super("Exception: Currency (" + symbol + ") not found!");
    }
}
