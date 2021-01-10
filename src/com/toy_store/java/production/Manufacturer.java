package com.toy_store.java.production;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Manufacturer implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;

    private final String name;
    private int countProducts;

    public Manufacturer(String name) {
        this.name = name;
        countProducts = 0;
    }

    public String getName() {
        return name;
    }

    void incCount() {
        countProducts++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manufacturer that = (Manufacturer) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
