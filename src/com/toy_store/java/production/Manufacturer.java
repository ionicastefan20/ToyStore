package com.toy_store.java.production;

import java.io.Serial;
import java.io.Serializable;

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
        if (o == null) return false;
        if (o instanceof String) return name.equals(o);
        if (getClass() != o.getClass()) return false;

        Manufacturer that = (Manufacturer) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + countProducts;
        return result;
    }

    @Override
    public String toString() {
        return "Manufacturer{ " +
                "name='" + name + '\'' +
                " }";
    }
}
