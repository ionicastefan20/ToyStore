package com.toy_store.java.utilities;

public class CSVLine {

    private final String uniqueId;
    private final String name;
    private final String manufacturer;
    private final double price;
    private final int quantity;

    public CSVLine(String uniqueId, String name, String manufacturer, double price, int quantity) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
