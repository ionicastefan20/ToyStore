package com.toy_store.java;

import com.toy_store.java.command_prompt.*;
import com.toy_store.java.marketing.Store;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import static com.toy_store.java.command_prompt.StoreKeeper.receiveCommand;
import static java.lang.System.*;

/**
 * The entry point of the program.
 */
public class Main {

    /**
     * Main method.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Store.getInstance().displayWelcome();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] words = scanner.nextLine().split(" ");

            switch (words[0]) {
                case "listcurrencies" -> receiveCommand(new ListCurrencies());

                case "getstorecurrency" -> receiveCommand(new GetStoreCurrency());

                case "addcurrency" -> receiveCommand(new AddCurrency(words[1], words[2], words[3]));

                case "loadcsv" -> receiveCommand(new LoadCSV(words[1]));

                case "savecsv" -> receiveCommand(new SaveCSV(words[1]));

                case "setstorecurrency" -> receiveCommand(new SetStoreCurrency(words[1]));

                case "updateparity" -> receiveCommand(new UpdateParity(words[1], words[2]));

                case "listproducts" -> receiveCommand(new ListProducts());

                case "showproduct" -> receiveCommand(new ShowProduct(words[1]));

                case "listmanufacturers" -> receiveCommand(new ListManufacturer());

                case "listproductsbymanufacturer" -> receiveCommand(new ListProductsByManufacturer(
                                Arrays.stream(words).skip(1)
                                        .reduce("", (s1, s2) -> s1 + " " + s2).trim())
                        );

                case "listdiscounts" -> receiveCommand(new ListDiscounts());

                case "adddiscount" -> receiveCommand(new AddDiscount(words[1], words[2],
                        Arrays.stream(words).skip(3)
                                .reduce("", (s1, s2) -> s1 + " " + s2).trim()));

                case "applydiscount" -> receiveCommand(new ApplyDiscount(words[1], words[2]));

                case "calculatetotal" -> receiveCommand(new CalculateTotal(
                        Arrays.stream(words).skip(1).collect(Collectors.toList())
                ));

                case "exit", "quit" -> receiveCommand(new Exit());

                case "loadstore" -> receiveCommand(new LoadStore(words[1]));

                case "savestore" -> receiveCommand(new SaveStore(words[1]));

                default -> out.println("Invalid command");
            }
        }

        StoreKeeper.executeCommands();
    }
}
