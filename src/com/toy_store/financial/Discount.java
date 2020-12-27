package com.toy_store.financial;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Discount implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;

    private String name;
    private DiscountType discountType;
    private double value;
    private LocalDateTime lastDateApplied;

    public Discount() {}

    void setAsAppliedNow() {
        this.lastDateApplied = LocalDateTime.now();
    }
}