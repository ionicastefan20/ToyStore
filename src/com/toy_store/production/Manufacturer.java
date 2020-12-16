package com.toy_store.production;

public class Manufacturer {
    private String name;
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
}
