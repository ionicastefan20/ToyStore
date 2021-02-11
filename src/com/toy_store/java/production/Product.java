package com.toy_store.java.production;

import com.toy_store.java.marketing.Discount;
import com.toy_store.java.marketing.DiscountType;
import com.toy_store.java.marketing.Store;
import com.toy_store.java.utilities.PriceFormatUtility;
import java.io.Serializable;

/**
 * Represents a product on the store.
 */
public class Product implements Serializable {

    /**
     * The ID of the product. It's unique.
     */
    private String uniqueId;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The manufacturer of the product.
     */
    private Manufacturer manufacturer;

    /**
     * The price of the product.
     */
    private double price;

    /**
     * The quantity of products of this type.
     */
    private int quantity;

    /**
     * Default constructor for <code>Product</code> class.
     */
    public Product() {
        // Do nothing
    }

    /**
     * Constructor with a parameter for <code>Product</code> class.
     * @param uniqueId the ID of the product.
     */
    public Product(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Getter for the ID.
     * @return the ID of the product
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Setter for the ID used in the builder.
     * @param uniqueId the ID of the product
     */
    void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Getter for the name.
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the product
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * @return the manufacturer's name
     */
    public String getManufacturerName() {
        return manufacturer.getName();
    }

    /**
     * @param manufacturer the manufacturer of the product
     */
    void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Applies a discount to the current product. Must be tested be beforehand for negative price.
     * @param discount the discount that will be applied to the product.
     */
    public void applyDiscount(Discount discount) {
        if (discount.getDiscountType() == DiscountType.PERCENTAGE_DISCOUNT) {
            price *= (100 - discount.getValue()) / 100;
        } else {
            price -= discount.getValue();
        }
    }

    /**
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity of the product
     */
    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Checks if this product has the same ID with the one given as parameter.
     * @param uniqueId the other product's ID
     * @return true if the products have the same ID
     */
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
