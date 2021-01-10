package com.toy_store.java.marketing;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Discount implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;

    private final String name;
    private final DiscountType discountType;
    private final double value;
    private LocalDateTime lastDateApplied;

    public Discount(String name, DiscountType discountType, double value) {
        this.name = name;
        this.discountType = discountType;
        this.value = value;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getValue() {
        return value;
    }

    public void setAsAppliedNow() {
        this.lastDateApplied = LocalDateTime.now();
    }

    @Override
    public String toString() {
        String out = discountType + " " + value + " " + name;
        if (lastDateApplied != null) out += " " + lastDateApplied;

        return out;
    }
}