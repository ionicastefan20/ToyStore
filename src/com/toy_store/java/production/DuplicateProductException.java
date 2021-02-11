package com.toy_store.java.production;

/**
 * Exception thrown when the a product is being added to the store and it already exists.
 */
public class DuplicateProductException extends Exception {

    /**
     * Constructor for <code>DuplicateProductException</code>
     * @param id the id of the product
     */
    public DuplicateProductException(String id) {
        super("Exception: Duplicate product (" + id + ")!");
    }
}
