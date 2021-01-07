package com.toy_store.java.marketing;

import com.toy_store.java.financial.*;
import com.toy_store.java.production.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.toy_store.java.utilities.CSVUtility;

// TODO redo javadoc
/**
 * Represents a Toy-Store.
 * <p
 *
 * The class is designed so that there's never more than one <code>Store</code> instance. Therefore,
 * there is no public constructor. You obtain a <code>Store</code> instance using the <code>getInstance()</code>
 * method.
 *
 * @author Stefan-Theodor Ionica
 */
public class Store implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;

    private static Store instance = null;
    private final String name;
    private Currency currency = Currency.getInstanceByName("EUR");
    private final List<Product> products = new ArrayList<>();
    private final List<Manufacturer> manufacturers = new ArrayList<>();
    private final List<Discount> discounts = new ArrayList<>();

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store("POO Store");
        }
        return instance;
    }

    private Store(String name) {
        this.name = name;
        instance = this;
    }

    public Product getProduct(String uniqueId) {
        return products.stream().filter(product -> product.equalsId(uniqueId)).findFirst().orElse(null);

    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Product[] readCSV(String filename) {
        return CSVUtility.readCSV(filename);
    }

    public void saveCSV(String filename) {
        CSVUtility.saveCSV(filename, products);
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

    public void addProduct(Product product) throws DuplicateProductException {
        if (products.contains(product)) {
            throw new DuplicateProductException();
        } else {
            products.add(product);
            try {
                addManufacturer(product.getManufacturer());
            } catch (DuplicateManufacturerException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void addManufacturer(Manufacturer manufacturer) throws DuplicateManufacturerException {
        if (manufacturers.contains(manufacturer)) {
            throw new DuplicateManufacturerException();
        } else {
            manufacturers.add(manufacturer);
        }
    }

    public void createCurrency(String name, String symbol, double parityToEur) {
        Currency.createInstance(name, symbol, parityToEur);
    }

    public void changeCurrency(Currency currency) throws CurrencyNotFoundException {
        if (!Currency.exists(currency.getName())) {
            throw new CurrencyNotFoundException();
        }

        for (Product product : products) {
            product.setPrice(product.getPrice() / this.currency.getParityToEur() * currency.getParityToEur());
        }

        this.currency = currency;
    }

    public Discount createDiscount() {
        return new Discount();
    }

    public void applyDiscount(Discount discount) throws DiscountNotFoundException, NegativePriceException {
        discounts.add(discount);
    }

    public Product[] getProductsByManufacturer(Manufacturer manufacturer) {
        List<Product> productArrayList = new ArrayList<>();
        for (Product product : products) {
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
