package com.toy_store.financial;

import java.time.LocalDateTime;

public class Discount {
    private String name;
    private DiscountType discountType;
    private double value;
    private LocalDateTime lastDateApplied;

    public Discount() {}

    void setAsAppliedNow() {
        this.lastDateApplied = LocalDateTime.now();
    }
}