package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;
import com.toy_store.java.financial.CurrencyNotFoundException;
import com.toy_store.java.marketing.Store;

public class SetStoreCurrency implements Command {

    private final String currencyName;

    public SetStoreCurrency(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public void execute() {
        try {
            Store.getInstance().changeCurrency(Currency.getInstanceByName(currencyName));
        } catch (CurrencyNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
