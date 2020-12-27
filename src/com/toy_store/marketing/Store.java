package com.toy_store.marketing;

import com.toy_store.financial.*;
import com.toy_store.production.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.toy_store.utilities.CSVUtility;

public class Store implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;

    private static Store instance = null;
    private final String name;
    private Currency currency;
    private final List<Product> products;
    private final List<Manufacturer> manufacturers;

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store("POO Store", Currency.createInstance("EUR"));
        }
        return instance;
    }

    private Store(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
        this.products = new ArrayList<>();
        this.manufacturers = new ArrayList<>();
        instance = this;
    }

    public Product[] readCSV(String filename) {
        return CSVUtility.readCSV(filename);
    }

    public void saveCSV(String filename) {
        CSVUtility.saveCSV(filename, products);
    }

    public void addProduct(Product product) throws DuplicateProductException {
        if (products.contains(product)) {
            throw new DuplicateProductException();
        } else {
            products.add(product);
        }
    }

    public void addManufacturer(Manufacturer manufacturer) throws DuplicateManufacturerException {
        if (manufacturers.contains(manufacturer)) {
            throw new DuplicateManufacturerException();
        } else {
            manufacturers.add(manufacturer);
        }
    }

    public static Store loadStore(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            instance = (Store) ois.readObject();
            return instance;
        }
    }

    public void saveStore(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    public Currency createCurrency() {
        return Currency.createInstance("EUR");
    }

    void changeCurrency(Currency currency) throws CurrencyNotFoundException {
        Currency.getInstance(currency.getSymbol());

        for (Product product : products) {
            product.setPrice(product.getPrice() / this.currency.getParityToEur() * currency.getParityToEur());
        }

        this.currency = currency;
    }

    public Discount createDiscount() {
        return new Discount();
    }

    public void applyDiscount(Discount discount) throws DiscountNotFoundException, NegativePriceException {}

    public Product[] getProductsByManufacturer(Manufacturer manufacturer) {
        List<Product> productArrayList = new ArrayList<>();
        for (Product product: products) {
            if (product.getManufacturer().equals(manufacturer))
                productArrayList.add(product);
        }

        return productArrayList.toArray(new Product[0]);
    }

    double calculateTotal(Product[] products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice() * product.getQuantity();
        }

        return sum;
    }
}
