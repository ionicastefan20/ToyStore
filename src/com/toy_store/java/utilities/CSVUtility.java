package com.toy_store.java.utilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.Manufacturer;
import com.toy_store.java.production.Product;
import com.toy_store.java.production.ProductBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtility {

    private CSVUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static Product[] readCSV(String filename) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            reader.readNext();
            List<Product> productArrayList = new ArrayList<>();
//            List<Manufacturer> manufacturerArrayList = new ArrayList<>();
//            Manufacturer manufacturer = null;
            String[] columns;

//            int count = 2;
            while ((columns = reader.readNext()) != null) {
//                System.out.println(count++);

                boolean alreadyIn = false;
                for (Product product : productArrayList) {
                    if (product.getUniqueId().equals(columns[0])) {
                        alreadyIn = true;
                        break;
                    }
                }
                if (alreadyIn) continue;

                /*
                manufacturer = new Manufacturer(columns[2]);
                int index = manufacturerArrayList.indexOf(manufacturer);
                if (index != -1) {
                    manufacturer = manufacturerArrayList.get(index);
                }*/
                Product product = new ProductBuilder()
                        .withUniqueId(columns[0])
                        .withName(columns[1])
                        .withManufacturer(new Manufacturer(columns[2]))
                        .withPrice(Helper.getPriceUtility(columns[3], Store.getInstance().getCurrency()))
//                        .withQuantity(Integer.parseInt(columns[4].split(Helper.QUANTITY_SEPARATOR)[0]))
                        .withQuantity(Integer.parseInt(columns[4].substring(0, columns[4].length()-4)))
                        .build();

                productArrayList.add(product);
//                manufacturerArrayList.add(manufacturer);
            }

            return productArrayList.toArray(new Product[0]);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return new Product[0];
    }

    public static void saveCSV(String filename, List<Product> products) {
        try (CSVWriter reader = new CSVWriter(new FileWriter(filename))) {
            String[] columns = {"uniq_id", "product_name", "manufacturer", "price", "number_available_in_stock"};
            reader.writeNext(columns);

            for (Product product : products) {
                columns[0] = product.getUniqueId();
                columns[1] = product.getName();
                columns[2] = product.getManufacturer().getName();
                columns[3] = String.valueOf(product.getPrice());
                columns[4] = String.valueOf(product.getPrice());

                reader.writeNext(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
