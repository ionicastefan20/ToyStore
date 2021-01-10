package com.toy_store.java.marketing;

import com.toy_store.java.financial.Currency;
import com.toy_store.java.financial.CurrencyNotFoundException;
import com.toy_store.java.financial.NegativePriceException;
import com.toy_store.java.production.*;
import com.toy_store.java.utilities.CSVLine;
import com.toy_store.java.utilities.CSVUtility;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

// TODO redo javadoc

/**
 * Represents a Toy-Store.
 * <p
 * <p>
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
    private Currency currency;
    private final Map<String, Product> products = new LinkedHashMap<>();
    private final Map<String, Manufacturer> manufacturers = new HashMap<>();
    private final Map<String, Discount> discounts = new HashMap<>();

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store("POO Store");
        }
        return instance;
    }

    private Store(String name) {
        this.name = name;
        try {
            this.currency = Currency.getInstanceByName("EUR");
        } catch (CurrencyNotFoundException e) {
            // Do nothing
        }

        instance = this;
    }

    public void loadCSV(String filename) {
        List<CSVLine> csvData = CSVUtility.readCSV(filename);

        for (CSVLine line : csvData) {
            Manufacturer manufacturer = manufacturers.get(line.getManufacturer());
            if (manufacturer == null) manufacturer = new Manufacturer(line.getManufacturer());
            try {
                addManufacturer(manufacturer);
            } catch (DuplicateManufacturerException e) {
//                out.println(e.getMessage());
            }

            Product product = products.get(line.getUniqueId());
            if (product == null) product = new ProductBuilder()
                    .withUniqueId(line.getUniqueId())
                    .withName(line.getName())
                    .withManufacturer(manufacturer)
                    .withPrice(line.getPrice())
                    .withQuantity(line.getQuantity())
                    .build();
            try {
                addProduct(product);
            } catch (DuplicateProductException e) {
//                out.println(e.getMessage());
            }
        }
    }

    public void saveCSV(String filename) {
        List<CSVLine> csvLines = products.values().stream().map(p -> new CSVLine(
                p.getUniqueId(),
                p.getName(),
                p.getManufacturer().getName(),
                p.getPrice(),
                p.getQuantity()
        )).collect(Collectors.toList());

        CSVUtility.saveCSV(filename, csvLines);
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
        if (products.containsKey(product.getUniqueId()))
            throw new DuplicateProductException();

        products.put(product.getUniqueId(), product);

    }

    public String getProduct(String uniqueId) {
        return products.get(uniqueId).toString();
    }

    public List<String> getProducts() {
        return products.values().stream().map(Product::toString).collect(Collectors.toList());
    }

    public List<String> getProductsByManufacturer(String manufacturerName) {
        return products.values().stream()
                .filter(product -> manufacturerName.equals(product.getManufacturer().getName()))
                .map(Product::toString)
                .collect(Collectors.toList());
    }

    public void addManufacturer(Manufacturer manufacturer) throws DuplicateManufacturerException {
        if (manufacturers.containsKey(manufacturer.getName()))
            throw new DuplicateManufacturerException();

        manufacturers.put(manufacturer.getName(), manufacturer);
    }

    public List<String> getManufacturers() {
        return manufacturers.values().stream().map(Manufacturer::toString).sorted().collect(Collectors.toList());
    }

    public Currency getCurrency() {
        return currency;
    }

    public void changeCurrency(String currencyName) throws CurrencyNotFoundException {
        Currency newCurrency = Currency.getInstanceByName(currencyName);
        if (newCurrency == null)
            throw new CurrencyNotFoundException(currencyName);

        products.values().forEach(product -> product.applyNewCurrency(currency, newCurrency));

        currency = newCurrency;
    }

    public void createDiscount(String name, DiscountType discountType, double value) {
        discounts.put(name, new Discount(name, discountType, value));
    }

    public void applyDiscount(String discountName) throws DiscountNotFoundException, NegativePriceException {
        if (!discounts.containsKey(discountName))
            throw new DiscountNotFoundException();
        Discount discount = discounts.get(discountName);

        if (discount.getDiscountType() == DiscountType.FIXED_DISCOUNT) {
            double minPrice = Collections.min(products.values().stream().map(Product::getPrice)
                    .collect(Collectors.toList()));
            if (minPrice < discount.getValue())
                throw new NegativePriceException();
        }

        products.values().forEach(product -> product.applyDiscount(discount));
    }

    public List<String> getDiscounts() {
        return discounts.values().stream().map(Discount::toString).collect(Collectors.toList());
    }

    public double calculateTotal(Set<String> idList) {
        return products.entrySet().stream()
                .filter(e -> idList.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .values().stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .reduce(0, Double::sum);
    }
}
