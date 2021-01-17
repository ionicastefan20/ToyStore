package com.toy_store.java.marketing;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a discount that can be applied to all the products present on the store.
 */
public class Discount implements Serializable {

    /**
     * The name of the discount.
     */
    private final String name;

    /**
     * The type of the discount.
     */
    private final DiscountType discountType;

    /**
     * The value of the discount.
     */
    private final double value;

    /**
     * The date and time the discount os applied.
     */
    private LocalDateTime lastDateApplied;

    /**
     * Constructor for the <code>Discount</code> class.
     * @param name the name of the discount
     * @param discountType the type of the discount
     * @param value the value of the discount
     */
    public Discount(String name, DiscountType discountType, double value) {
        this.name = name;
        this.discountType = discountType;
        this.value = value;
    }

    /**
     * Getter for the type of the discount.
     * @return the type of the discount
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Getter for the value of the discount.
     * @return the value of the discount
     */
    public double getValue() {
        return value;
    }

    /**
     * Set the discount as being applied at the current date and time.
     */
    public void setAsAppliedNow() {
        this.lastDateApplied = LocalDateTime.now();
    }

    @Override
    public String toString() {
        String out = discountType + " " + value + " " + name + " ";
        out += (lastDateApplied == null) ? "\"Not applied yet\"" : lastDateApplied.toString();
        if (lastDateApplied != null) out += " " + lastDateApplied;

        return out;
    }
}