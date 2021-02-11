package com.toy_store.java.utilities;

import com.toy_store.java.marketing.Store;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class that parses CSV files containing the products of the store.
 */
public class CSVUtility {

    /**
     * The constructor CANNOT be used. It's an utility class.
     */
    private CSVUtility() {
        throw new IllegalStateException("Utility class cannot be instantiated.");
    }

    /**
     * Reads the CSV file given as parameter.
     *
     * @param filename the name of the file
     * @return A list of lines (<code>CSVLine</code>) containing information about the products
     */
    public static List<CSVLine> readCSV(String filename) {
        try (CSVParser csvParser = new CSVParser(new FileReader(filename), CSVFormat.DEFAULT
                .withHeader())) {

            List<CSVLine> csvData = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                if ("".equals(csvRecord.get(0)) ||
                        "".equals(csvRecord.get(1)) ||
                        "".equals(csvRecord.get(3))) continue;
                String manufacturerString = "".equals(csvRecord.get(2)) ? "Not Available" :
                        csvRecord.get(2);
                String priceString = csvRecord.get(3).split(" ")[0];
                String quantityString = "".equals(csvRecord.get(4)) ? "0" :
                        csvRecord.get(4).split("\u00A0| ")[0];
                CSVLine line = new CSVLine(
                        csvRecord.get(0),
                        csvRecord.get(1),
                        manufacturerString,
                        PriceFormatUtility.getPriceFromString(priceString,
                                Store.getInstance().getCurrency()),
                        Integer.parseInt(quantityString)
                );

                csvData.add(line);
            }

            return csvData;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    /**
     * Creates a CSV file with the information from the list of lines (<code>CSVLine</code>).
     *
     * @param filename the name of the file
     * @param csvLines the list of lines (<code>CSVLine</code>)
     */
    public static void saveCSV(String filename, List<CSVLine> csvLines) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filename), CSVFormat.DEFAULT
                .withHeader("uniq_id", "product_name", "manufacturer", "price",
                        "number_available_in_stock"))) {
            for (CSVLine line : csvLines) {
                csvPrinter.printRecord(
                        line.getUniqueId(),
                        line.getName(),
                        line.getManufacturerName(),
                        PriceFormatUtility.getPriceAsString(line.getPrice(),
                                Store.getInstance().getCurrency()),
                        line.getQuantity() + " NEW"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
