package com.toy_store.production;

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
    public String toString() {
        return "Manufacturer{ " +
                "name='" + name + '\'' +
                " }";
    }
}
