package com.toy_store.java.production;

public class DuplicateManufacturerException extends Exception {
    public DuplicateManufacturerException() {
        super("Duplicate manufacturer");
    }
}
