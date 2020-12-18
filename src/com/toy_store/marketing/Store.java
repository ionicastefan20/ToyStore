package com.toy_store.marketing;

import com.opencsv.exceptions.CsvValidationException;
import com.toy_store.financial.*;
import com.toy_store.production.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class Store implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;
    private static final String DEFAULT_STORE_NAME = "POO_Store";

    private static Store instance = null;
    private final String name;
    private Currency currency;
    private Product[] products;
    private int productsNum;
    private Manufacturer[] manufacturers;
    private int manufacturersNum;

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store(DEFAULT_STORE_NAME, Currency.getInstance());
        }
        return instance;
    }

    private Store(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
        this.products = null;
        this.productsNum = 0;
        this.manufacturers = null;
        this.manufacturersNum = 0;
        instance = this;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getProductsNum() {
        return productsNum;
    }

    public Product[] readCSV(String filename) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            reader.readNext();
            ArrayList<Product> productArrayList = new ArrayList<>();
            String[] columns;

            while ((columns = reader.readNext()) != null) {
                boolean alreadyIn = false;
                for (Product product : productArrayList) {
                    if (product.getUniqueId().equals(columns[0])) {
                        alreadyIn = true;
                        break;
                    }
                }
                if (alreadyIn) continue;

                Product product = new Product()
                        .setUniqueId(columns[0])
                        .setName(columns[1])
                        .setManufacturer(new Manufacturer(columns[2]))
                        .setPrice(Helper.convertStringToPriceCurrency(columns[3]).getLeft());

                String quantityNum = columns[4].equals("") ? "0" : columns[4].split(Helper.QUANTITY_SEPARATOR)[0];
                product.setQuantity(Integer.parseInt(quantityNum));
                productArrayList.add(product);
            }

            return productArrayList.toArray(new Product[0]);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return new Product[0];
    }

    public void addProduct(Product product) throws DuplicateProductException {
        if (products == null) {
            products = new Product[1];
        } else {
            if (Arrays.asList(products).contains(product)) {
                throw new DuplicateProductException();
            }

            if (productsNum == products.length) {
                Product[] productsClone = products;
                products = new Product[products.length * 2];
                System.arraycopy(productsClone, 0, products, 0, productsNum);
            }
        }

        products[productsNum++] = product;
    }

    public void addManufacturer(Manufacturer manufacturer) throws DuplicateManufacturerException {
        if (manufacturers == null) {
            manufacturers = new Manufacturer[1];
        } else {
            if (Arrays.asList(manufacturers).contains(manufacturer)) {
                throw new DuplicateManufacturerException();
            }

            if (manufacturersNum == manufacturers.length) {
                Manufacturer[] manufacturersClone = manufacturers;
                manufacturers = new Manufacturer[manufacturers.length * 2];
                System.arraycopy(manufacturersClone, 0, manufacturers, 0, manufacturersNum);
            }
        }

        manufacturers[manufacturersNum++] = manufacturer;
    }

    public void saveStore(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    public static Store loadStore(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(filename))) {
            instance = (Store) oos.readObject();
            return instance;
        }
    }

    void changeCurrency(Currency currency) throws CurrencyNotFoundException {
        if (Currency.getInstance(currency.getSymbol()) == null) throw new CurrencyNotFoundException();

        for (int i = 0; i < productsNum; i++) {
            products[i].setPrice(products[i].getPrice() / this.currency.getParityToEur() * currency.getParityToEur());
        }
        this.currency = currency;
    }

    void applyDiscount(Discount discount) throws DiscountNotFoundException, NegativePriceException {
    }

    Product[] getProductsByManufacturer(Manufacturer manufacturer) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        for (int i = 0; i < productsNum; i++) {
            if (products[i].getManufacturer().equals(manufacturer)) productArrayList.add(products[i]);
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
