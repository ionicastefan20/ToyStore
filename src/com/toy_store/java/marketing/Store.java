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

/**
 * Represents a Toy-Store. <br>
 * The class is designed so that there's never more than one <code>Store</code> instance. Therefore,
 * there is no public constructor. You obtain a <code>Store</code> instance using the
 * <code>getInstance()</code> method.
 *
 * @author Stefan-Theodor Ionica
 */
public class Store implements Serializable {

    /**
     * The name of the store.
     */
    private final String name = "POO Store";

    /**
     * The currency of the store.
     */
    private Currency currency;

    /**
     * The map of products. The key is the ID of the product.
     */
    private final Map<String, Product> products = new LinkedHashMap<>();

    /**
     * The map of manufacturers. The key is the name of the manufacturer.
     */
    private final Map<String, Manufacturer> manufacturers = new HashMap<>();
    private final Map<String, Discount> discounts = new HashMap<>();

    /**
     * Bill Pugh Singleton implementation.
     */
    private static class SingletonHelper {
        private static Store instance = new Store();
    }

    /**
     * Method used to get the instance of the store.
     * @return the instance of the store
     */
    public static Store getInstance() {
        return SingletonHelper.instance;
    }

    /**
     * Constructor for the <code>Store</code> class.
     */
    private Store() {
        try {
            this.currency = Currency.getInstanceByName("EUR");
        } catch (CurrencyNotFoundException e) {
            out.println(e.getMessage() + " Exiting...");
            exit(1);
        }
    }

    /**
     * Displays a message on startup.
     */
    public void displayWelcome() {
        out.println("Welcome to \"" + name + "\"!\n");
    }

    /**
     * Loads a list of products from a CSV file.
     * @param filename the name of the file
     */
    public void loadCSV(String filename) {
        List<CSVLine> csvData = CSVUtility.readCSV(filename);

        for (CSVLine line : csvData) {
            Manufacturer manufacturer = manufacturers.get(line.getManufacturerName());
            if (manufacturer == null) manufacturer = new Manufacturer(line.getManufacturerName());
            addManufacturer(manufacturer);


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
                out.println(e.getMessage());
            }
        }
    }

    /**
     * Save all products present on the store into a CSV file.
     * @param filename the name of the file
     */
    public void saveCSV(String filename) {
        List<CSVLine> csvLines = products.values().stream().map(p -> new CSVLine(
                p.getUniqueId(),
                p.getName(),
                p.getManufacturerName(),
                p.getPrice(),
                p.getQuantity()
        )).collect(Collectors.toList());

        CSVUtility.saveCSV(filename, csvLines);
    }

    /**
     * Loads a previous state of the store from a file. The current state of the store si lost!
     * @param filename the name of the file
     * @throws IOException if the file does not exists
     * @throws ClassNotFoundException if the file does not contain a store
     */
    public static void loadStore(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            SingletonHelper.instance = (Store) ois.readObject();
        }
    }

    /**
     * Saves the current state of the store into a file.
     * @param filename the name of the file
     * @throws IOException if an I/O error occurred
     */
    public void saveStore(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    /**
     * Adds a product to the store.
     * @param product the product to be added
     * @throws DuplicateProductException if the product already exists in the store
     */
    public void addProduct(Product product) throws DuplicateProductException {
        if (products.containsKey(product.getUniqueId()))
            throw new DuplicateProductException(product.getUniqueId());

        products.put(product.getUniqueId(), product);

    }

    /**
     * Gets the String representation of a product from the store based on its ID.
     * @param uniqueId the ID of the product
     * @return the String representation of the product
     * @throws ProductNotFoundException if the product does not exists
     */
    public String getProduct(String uniqueId) throws ProductNotFoundException {
        Product product = products.get(uniqueId);
        if (product == null) throw new ProductNotFoundException(uniqueId);

        return product.toString();
    }

    /**
     * Returns a list of all products from the store (as Strings).
     * @return the list of products
     */
    public List<String> getProducts() {
        return products.values().stream()
                .map(Product::toString)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of products (as Strings) produced by the manufacturer given as parameter.
     * @param manufacturerName the name of the manufacturer
     * @return the list of products
     */
    public List<String> getProductsByManufacturer(String manufacturerName) throws ManufacturerNotFoundException {
        if (!manufacturers.containsKey(manufacturerName))
            throw new ManufacturerNotFoundException(manufacturerName);
        return products.values().stream()
                .filter(product -> manufacturerName.equals(product.getManufacturerName()))
                .map(Product::toString)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new manufacturer to the store.
     * @param manufacturer the manufacturer to be added.
     */
    public void addManufacturer(Manufacturer manufacturer) {
        if (manufacturers.containsKey(manufacturer.getName()))
            return;

        manufacturers.put(manufacturer.getName(), manufacturer);
    }

    /**
     * Returns a list of all manufacturers (as Strings) present on the store.
     * @return the list of manufacturers
     */
    public List<String> getManufacturers() {
        return manufacturers.values().stream()
                .map(Manufacturer::toString)
                .filter(s -> !("Not Available".equals(s)))
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Gets the currency of the store.
     * @return the currency of the store
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Changes the current currency of the store to the one given as parameter.
     * @param currencyName the name of the new currency
     * @throws CurrencyNotFoundException if the currency does not exists
     */
    public void changeCurrency(String currencyName) throws CurrencyNotFoundException {
        Currency newCurrency = Currency.getInstanceByName(currencyName);
        if (newCurrency == null)
            throw new CurrencyNotFoundException(currencyName);

        products.values().forEach(product -> product.setPrice(Currency.convertPrice(
                product.getPrice(), currency, newCurrency)));

        currency = newCurrency;
    }

    /**
     * Created a new discount for the products on the store.
     * @param name the name of the discount
     * @param discountType the type of the discount
     * @param value the value of the discount
     */
    public void createDiscount(String name, DiscountType discountType, double value) {
        discounts.put(discountType.name().replace("_DISCOUNT", "") + value,
                new Discount(name, discountType, value));
    }

    /**
     * Applies an existing discount to all products in the store. If any of the prices goes
     * negative, the discount won't be applied.
     * @param type the type of the discount
     * @param value the value of the discount
     * @throws DiscountNotFoundException if the dicount does not exist
     * @throws NegativePriceException the any of the prices goes negative
     */
    public void applyDiscount(String type, double value) throws DiscountNotFoundException,
            NegativePriceException {
        String key = type + value;
        if (!discounts.containsKey(key))
            throw new DiscountNotFoundException(type);
        Discount discount = discounts.get(key);

        if (discount.getDiscountType() == DiscountType.FIXED_DISCOUNT) {
            double minPrice = Collections.min(products.values().stream().map(Product::getPrice)
                    .collect(Collectors.toList()));
            if (minPrice < discount.getValue())
                throw new NegativePriceException();
        }
        else if (value > 100) throw new NegativePriceException();

        products.values().forEach(product -> product.applyDiscount(discount));
        discount.setAsAppliedNow();
    }

    /**
     * Gets a list of all discounts (as Strings) in the store.
     * @return the list of discounts
     */
    public List<String> getDiscounts() {
        return discounts.values().stream()
                .map(Discount::toString)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the total price of the products from the list given as parameter.
     * @param idList the list of IDs
     * @return the total price of the products
     * @throws ProductNotFoundException if any of the products does not exist
     */
    public double calculateTotal(List<String> idList) throws ProductNotFoundException {
        for (String id : idList) {
            if(!products.containsKey(id)) throw new ProductNotFoundException(id);
        }

        Map<String, Long> idMap = idList.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        List<Product> productList = products.entrySet().stream()
                .filter(p -> idMap.containsKey(p.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        for (Product product : productList) {
            if (product.getQuantity() < idMap.get(product.getUniqueId())) {
                try {
                    throw new NotEnoughItemsException(product.getUniqueId());
                } catch (NotEnoughItemsException e) {
                    out.println(e.getMessage());
                }

                idMap.replace(product.getUniqueId(), (long) product.getQuantity());
            }
        }

        return productList.stream()
                .filter(p -> idMap.containsKey(p.getUniqueId()))
                .mapToDouble(p -> p.getPrice() * idMap.get(p.getUniqueId()))
                .reduce(0, Double::sum);
    }
}
