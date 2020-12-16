package com.toy_store.production;

public class DuplicateProductException extends Exception {
    public DuplicateProductException() {
        super("Duplicate product");
    }
}
