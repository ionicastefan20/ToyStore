package com.toy_store.java.production;

import com.toy_store.java.financial.Currency;
import com.toy_store.java.marketing.Discount;
import com.toy_store.java.marketing.DiscountType;
import com.toy_store.java.marketing.Store;
import com.toy_store.java.utilities.PriceFormatUtility;

import java.io.Serial;
import java.io.Serializable;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;

    private String uniqueId;
    private String name;
    private Manufacturer manufacturer;
    private double price;
    private int quantity;

    public Product() {
        // Do nothing
    }

    public Product(String uniqueId) {
        this.uniqueId = uniqueId;
    }


    public String getUniqueId() {
        return uniqueId;
    }

    void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }

    public void applyNewCurrency(Currency oldCurrency, Currency newCurrency) {
        price = price * oldCurrency.getParityToEur() / newCurrency.getParityToEur();
    }

    public void applyDiscount(Discount discount) {
        if (discount.getDiscountType() == DiscountType.PERCENTAGE_DISCOUNT) {
            price *= (100 - discount.getValue()) / 100;
        } else {
            price -= discount.getValue();
        }
    }

    public int getQuantity() {
        return quantity;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean equalsId(String uniqueId) {
        return uniqueId.equals(this.uniqueId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return uniqueId.equals(product.uniqueId);
    }

    @Override
    public int hashCode() {
        return uniqueId != null ? uniqueId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return uniqueId + "," + name + "," + manufacturer.toString() + "," +
                PriceFormatUtility.getPriceAsString(price, Store.getInstance().getCurrency()) +
                "," + quantity;
    }
}
