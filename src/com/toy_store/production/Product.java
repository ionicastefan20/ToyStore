package com.toy_store.production;

import com.toy_store.financial.Discount;

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
    private Discount discount;

    public Product() {}

    public Product(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Product setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Product setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        manufacturer.incCount();
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Discount getDiscount() {
        return discount;
    }

    public Product setDiscount(Discount discount) {
        this.discount = discount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return uniqueId.equals(product.uniqueId);
    }

    @Override
    public String toString() {
        return "Product{ " +
                "\n\tuniqueId='" + uniqueId + '\'' +
                ",\n\tname='" + name + '\'' +
                ",\n\tmanufacturer=" + manufacturer.toString() +
                ",\n\tprice=" + price +
                ",\n\tquantity=" + quantity +
                ",\n\tdiscount=" + discount +
                " \n}";
    }
}
