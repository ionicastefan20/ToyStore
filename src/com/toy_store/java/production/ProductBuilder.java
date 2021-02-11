package com.toy_store.java.production;

/**
 * Class that represents the builder for a <code>Product</code>.
 */
public class ProductBuilder {

    /**
     * The product that will be built.
     */
    private final Product product;

    /**
     * Constructor for <code>ProductBuilder</code> class.
     */
    public ProductBuilder() {
        this.product = new Product();
    }

    /**
     * Sets the ID for the product.
     * @param uniqueId the ID of the product
     * @return the builder
     */
    public ProductBuilder withUniqueId(String uniqueId) {
        product.setUniqueId(uniqueId);
        return this;
    }

    /**
     * Sets the name for the product.
     * @param name the name of the product
     * @return the builder
     */
    public ProductBuilder withName(String name) {
        product.setName(name);
        return this;
    }

    /**
     * Sets the manufacturer for the product.
     * @param manufacturer the manufacturer of the product
     * @return the builder
     */
    public ProductBuilder withManufacturer(Manufacturer manufacturer) {
        product.setManufacturer(manufacturer);
        manufacturer.incCount();
        return this;
    }

    /**
     * Sets the price for the product.
     * @param price the price of the product
     * @return the builder
     */
    public ProductBuilder withPrice(double price) {
        product.setPrice(price);
        return this;
    }

    /**
     * Sets the quantity for the product.
     * @param quantity the quantity of the product
     * @return the builder
     */
    public ProductBuilder withQuantity(int quantity) {
        product.setQuantity(quantity);
        return this;
    }

    /**
     * Builds the product.
     * @return the product
     */
    public Product build() {
        return product;
    }
}
