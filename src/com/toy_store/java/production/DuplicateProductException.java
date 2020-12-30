package com.toy_store.java.production;

public class DuplicateProductException extends Exception {
    public DuplicateProductException() {
        super("Duplicate product");
    }
}
