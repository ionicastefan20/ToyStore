package com.toy_store.java.marketing;

/**
 * Exception thrown when a discount cannot be found on the store.
 */
public class DiscountNotFoundException extends Exception {

    /**
     * Constructor for <code>DiscountNotFoundException</code> class.
     * @param name the name of the discount
     */
    public DiscountNotFoundException(String name) {
        super("Exception: Discount (" + name + ") does not exist");
    }
}
