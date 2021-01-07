package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;

public class ListCurrencies implements Command {

    @Override
    public void execute() {
        for (String currencyString : Currency.getAvailableCurrencies()) {
            System.out.println(currencyString);
        }
    }
}
