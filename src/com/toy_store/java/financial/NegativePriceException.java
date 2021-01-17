package com.toy_store.java.financial;

/**
 * Exception thrown when a price reaches a negative value.
 */
public class NegativePriceException extends Exception {

    /**
     * Constructor for NegativePriceException class.
     */
    public NegativePriceException() {
        super("Exception: Negative price!");
    }
}
