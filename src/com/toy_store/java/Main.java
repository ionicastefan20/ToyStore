package com.toy_store.java;

import com.toy_store.java.command_prompt.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.lang.System.*;

public class Main {

    private static void setInput() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("input");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setIn(fis);
    }

    public static void main(String[] args) {
        setInput();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] words = scanner.nextLine().split(" ");

            switch (words[0]) {
                case "listcurrencies" -> StoreKeeper.receiveCommand(new ListCurrencies());
                case "getstorecurrency" -> StoreKeeper.receiveCommand(new GetStoreCurrency());
                case "addcurrency" -> StoreKeeper.receiveCommand(new AddCurrency(words[1], words[2],
                        Double.parseDouble(words[3])));
                case "loadcsv" -> StoreKeeper.receiveCommand(new LoadCSV(words[1]));
                case "savecsv" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "setstorecurrency" -> StoreKeeper.receiveCommand(new SetStoreCurrency(words[1]));
                case "updateparity" -> StoreKeeper.receiveCommand(new UpdateParity(words[1],
                        Double.parseDouble(words[2])));
                case "listproducts" -> StoreKeeper.receiveCommand(new ListProducts());
                case "showproduct" -> StoreKeeper.receiveCommand(new ShowProduct(words[1]));
                case "listmanufacturers" -> StoreKeeper.receiveCommand(new ListManufacturer());
                case "listproductsbymanufacturarer" -> StoreKeeper.receiveCommand(
                        new ListProductsByManufacturer(words[1]));
                case "listdiscounts" -> StoreKeeper.receiveCommand(new ListDiscounts());
                case "addiscount" -> StoreKeeper.receiveCommand(new AddDiscount(words[1],
                        Double.parseDouble(words[2]), words[3]));
                case "applydiscount" -> StoreKeeper.receiveCommand(new ApplyDiscount(words[1]));
                case "calculatetotal" -> StoreKeeper.receiveCommand(new CalculateTotal(
                        Arrays.stream(words).skip(1).collect(Collectors.toSet())
                ));
                case "exit", "quit" -> StoreKeeper.receiveCommand(new Exit());
                // TODO
                case "savestore" -> StoreKeeper.receiveCommand(new Exit());
                case "loadstore" -> StoreKeeper.receiveCommand(new Exit());
                default -> out.println("Invalid command");
            }
        }

        StoreKeeper.executeCommands();
    }
}
