package com.toy_store.java;

import com.toy_store.java.command_prompt.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
                case "loadcsv" -> StoreKeeper.receiveCommand(new LoadCSV(words[1]));
                case "savecsv" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "listcurrencies" -> StoreKeeper.receiveCommand(new ListCurrencies());
                case "getstorecurrency" -> StoreKeeper.receiveCommand(new GetStoreCurrency());
                case "addcurrency" -> StoreKeeper.receiveCommand(new AddCurrency(words[1], words[2],
                        Double.parseDouble(words[3])));
                // TODO
                case "setstorecurrency" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "updateparity" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "listproducts" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "listproductsbymanufacturarer" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "showproduct" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "listmanufacturers" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "listdiscounts" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "addiscount" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "applydiscount" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "calculatetotal" -> StoreKeeper.receiveCommand(new SaveCSV(words[1]));
                case "exit", "quit" -> StoreKeeper.receiveCommand(new Exit());
                case "savestore" -> StoreKeeper.receiveCommand(new Exit());
                case "loadstore" -> StoreKeeper.receiveCommand(new Exit());
                default -> System.out.println("Invalid command");
            }
        }

        StoreKeeper.executeCommands();
    }
}
