package com.toy_store.marketing;

import com.opencsv.exceptions.CsvValidationException;
import com.toy_store.financial.*;
import com.toy_store.production.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class Store {
    private static final String DEFAULT_STORE_NAME = "POO_Store";
    private static Store instance = null;
    private String name;
    private Currency currency;
    private Product[] products;
    private int productsNum;
    private Manufacturer[] manufacturers;
    private int manufacturersNum;

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store(DEFAULT_STORE_NAME, new Currency());
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

    public Product[] readCSV(String filename) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            ArrayList<Product> productArrayList = new ArrayList<>();
            String[] columns = reader.readNext();

            int count = 2;
            while ((columns = reader.readNext()) != null) {
                System.out.print((count++)+" "+columns.length + " ");
                if (columns.length == 1) {
                    System.out.println();
                    continue;
                }
                Product product = new Product()
                        .setUniqueId(columns[0])
                        .setName(columns[1])
                        .setManufacturer(new Manufacturer(columns[2]))
                        .setPrice(Helper.convertStringToPriceCurrency(columns[3]).getLeft());
                String quantityNum = columns[4].equals("") ? "0" : columns[4].split(Helper.SEPARATOR)[0];
                product.setQuantity(Integer.parseInt(quantityNum));
                productArrayList.add(product);
                System.out.println(columns[0]+" "+columns[1]+" "+quantityNum+"a");
            }

            Product[] productsArray = new Product[productArrayList.size()];
            return productArrayList.toArray(productsArray);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return new Product[0];
    }

    void addProduct(Product product) throws DuplicateProductException {
        if (Arrays.asList(products).contains(product)) {
            throw new DuplicateProductException();
        }

        if (productsNum == products.length) {
            Product[] productsClone = products;
            products = new Product[products.length * 2];
            System.arraycopy(productsClone, 0, products, 0, productsNum);
        }

        products[productsNum++] = product;
    }

    void addManufacturer(Manufacturer manufacturer) throws DuplicateManufacturerException {
        if (Arrays.asList(manufacturers).contains(manufacturer)) {
            throw new DuplicateManufacturerException();
        }

        if (manufacturersNum == manufacturers.length) {
            Manufacturer[] manufacturersClone = manufacturers;
            manufacturers = new Manufacturer[manufacturers.length * 2];
            System.arraycopy(manufacturersClone, 0, manufacturers, 0, manufacturersNum);
        }

        manufacturers[manufacturersNum++] = manufacturer;
    }

    void loadStore(String filename) throws IOException {
    }

    void saveStore(String filename) throws IOException {
    }

    void changeCurrency(Currency currency) throws CurrencyNotFoundException {
    }

    void applyDiscount(Discount discount) throws DiscountNotFoundException, NegativePriceException {
    }

    Product[] getProductsByManufacturer(Manufacturer manufacturer) {
        return new Product[1];
    }

    double calculateTotal(Product[] product) {
        return 1.0;
    }
}
