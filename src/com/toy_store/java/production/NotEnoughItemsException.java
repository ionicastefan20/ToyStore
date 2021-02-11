package com.toy_store.java.production;

/**
 * Exception thrown when the user tries to calculate the total of a product with the quantity to low.
 */
public class NotEnoughItemsException extends Exception {

    /**
     * Constructor for <code>NotEnoughItemsException</code>
     * @param id the id of the product
     */
    public NotEnoughItemsException(String id) {
        super("Exception: There are not enough items for product (" + id + ")!");
    }
}
