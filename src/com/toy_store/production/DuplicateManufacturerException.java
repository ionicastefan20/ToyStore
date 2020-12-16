package com.toy_store.production;

public class DuplicateManufacturerException extends Exception {
    public DuplicateManufacturerException() {
        super("Duplicate manufacturer");
    }
}
