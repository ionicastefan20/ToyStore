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

public class CSVUtility {

    private CSVUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static List<CSVLine> readCSV(String filename) {
        try (CSVParser csvParser = new CSVParser(new FileReader(filename), CSVFormat.DEFAULT
                .withIgnoreHeaderCase())) {

            List<CSVLine> csvData = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                CSVLine line = new CSVLine(
                        csvRecord.get(0),
                        csvRecord.get(1),
                        csvRecord.get(2),
                        PriceFormatUtility.getPriceFromString(csvRecord.get(3), Store.getInstance().getCurrency()),
                        Integer.parseInt(csvRecord.get(4).split("\u00A0")[0])
                );

                csvData.add(line);
            }

            return csvData;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public static void saveCSV(String filename, List<CSVLine> csvLines) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filename), CSVFormat.DEFAULT
                .withHeader("uniq_id", "product_name", "manufacturer", "price", "number_available_in_stock"))) {
            for (CSVLine line : csvLines) {
                csvPrinter.printRecord(
                        line.getUniqueId(),
                        line.getName(),
                        line.getManufacturer(),
                        PriceFormatUtility.getPriceAsString(line.getPrice(), Store.getInstance().getCurrency()),
                        line.getQuantity() + "\u00A0NEW"
                );
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
