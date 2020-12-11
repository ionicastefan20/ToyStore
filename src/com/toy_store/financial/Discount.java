package com.toy_store.financial;

import java.time.LocalDateTime;

public class Discount {
    private String name;
    private DiscountType discountType;
    private double value;
    private LocalDateTime lastDateApplied;

    public Discount() {}

    public String getName() {
        return name;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getLastDateApplied() {
        return lastDateApplied;
    }

    void setAsAppliedNow() {
        this.lastDateApplied = LocalDateTime.now();
    }
}