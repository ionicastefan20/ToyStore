package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;
import com.toy_store.java.financial.CurrencyNotFoundException;
import static java.lang.System.*;

public class UpdateParity implements Command {

    private final String currencyName;
    private final double parity;

    public UpdateParity(String currencyName, String parity) {
        this.currencyName = currencyName;
        this.parity = Double.parseDouble(parity);
    }

    @Override
    public void execute() {
        try {
            Currency.getInstanceByName(currencyName).updateParity(parity);
        } catch (CurrencyNotFoundException e) {
            e.printStackTrace();
            out.println(e.getMessage());
        }
    }
}
