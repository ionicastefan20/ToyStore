package com.toy_store.java.utilities;

/**
 * Class that represents a line in the CSV file containing the products. It's used as a separator
 * between the CSV parser (CSVUtility) and the store.
 */
public class CSVLine {

    /**
     * The ID of the product.
     */
    private final String uniqueId;

    /**
     * The name of the product.
     */
    private final String name;

    /**
     * The name of the manufacturer.
     */
    private final String manufacturerName;

    /**
     * The price of the product.
     */
    private final double price;

    /**
     * The quantity of the product.
     */
    private final int quantity;

    /**
     * Constructor for <code>CSVLine</code> class.
     *
     * @param uniqueId         the ID
     * @param name             the name
     * @param manufacturerName the name of the manufacturer
     * @param price            the price
     * @param quantity         the quantity
     */
    public CSVLine(String uniqueId, String name, String manufacturerName, double price,
                   int quantity) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.manufacturerName = manufacturerName;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return the ID of the product
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * @return the name of the manufacturer
     */
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }
}
