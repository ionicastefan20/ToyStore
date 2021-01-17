package com.toy_store.java.production;

/**
 * Exception thrown when a product is not found.
 */
public class ProductNotFoundException extends Exception {

    /**
     * Constructor for <code>ProductNotFoundException</code>
     * @param id the id of the product
     */
    public ProductNotFoundException(String id) {
        super("Exception: Product (" + id + ") not found!");
    }
}
