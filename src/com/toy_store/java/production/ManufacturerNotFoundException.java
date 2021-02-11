package com.toy_store.java.production;

/**
 * Exception thrown when a manufacturer is not found.
 */
public class ManufacturerNotFoundException extends Exception {

    /**
     * Constructor for <code>ManufacturerNotFoundException</code>
     * @param name the name of the manufacturer
     */
    public ManufacturerNotFoundException(String name) {
        super("Exception: Manufacturer (" + name + ") not found!");
    }
}
