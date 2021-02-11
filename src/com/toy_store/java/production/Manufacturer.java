package com.toy_store.java.production;

import java.io.Serializable;

/**
 * Class that represents a manufacturer for a specific product.
 */
public class Manufacturer implements Serializable {

    /**
     * The name of the manufacturer.
     */
    private final String name;

    /**
     * The number of products on the store from this manufacturer.
     */
    private int countProducts;

    /**
     * Constructor for <code>Manufacturer</code> class.
     * @param name the name of the manufacturer
     */
    public Manufacturer(String name) {
        this.name = name;
        countProducts = 0;
    }

    /**
     * Getter for the name.
     * @return the name of the manufacturer
     */
    public String getName() {
        return name;
    }

    /**
     * Increments the number of products that are produced by this manufacturer, a new product is
     * being added to the store.
     */
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
