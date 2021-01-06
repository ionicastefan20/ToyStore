package com.toy_store.java.utilities;

import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.Manufacturer;
import com.toy_store.java.production.Product;
import com.toy_store.java.production.ProductBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

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
        try (CSVParser csvParser = new CSVParser(new FileReader(filename), CSVFormat.DEFAULT
                .withIgnoreHeaderCase())) {
            List<Product> productArrayList = new ArrayList<>();
            List<String> uniqueIdArrayList = new ArrayList<>();
            List<String> manufacturerArrayList = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                // check if csv is broken
                boolean emptyField = false;
                for (String field : csvRecord.toMap().values()) {
                    if ("".equals(field)) {
                        emptyField = true;
                        break;
                    }
                }

                // check if product is already in
                if (uniqueIdArrayList.contains(csvRecord.get(0)) ||
                        manufacturerArrayList.contains(csvRecord.get(2)) ||
                        emptyField) {
                    continue;
                }

                Product product = new ProductBuilder()
                        .withUniqueId(csvRecord.get(0))
                        .withName(csvRecord.get(1))
                        .withManufacturer(new Manufacturer(csvRecord.get(2)))
                        .withPrice(Helper.getPriceUtility(csvRecord.get(3), Store.getInstance().getCurrency()))
                        .withQuantity(Integer.parseInt(csvRecord.get(4).split(Helper.QUANTITY_SEPARATOR)[0]))
                        .build();

                productArrayList.add(product);
                uniqueIdArrayList.add(csvRecord.get(0));
                manufacturerArrayList.add(csvRecord.get(2));
            }

            return productArrayList.toArray(new Product[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Product[0];
    }

    public static void saveCSV(String filename, List<Product> products) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filename), CSVFormat.DEFAULT
                .withHeader("uniq_id", "product_name", "manufacturer", "price", "number_available_in_stock"))) {
            for (Product product : products) {
                csvPrinter.printRecord(
                        product.getUniqueId(),
                        product.getName(),
                        product.getManufacturer().getName(),
                        Helper.getPriceString(product.getPrice(), Store.getInstance().getCurrency()),
                        product.getQuantity() + Character.toString(160) + "NEW"
                );
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
