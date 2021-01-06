package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;

public class UpdateParity implements Command {

    private final String currencyName;
    private final double parity;

    public UpdateParity(String currencyName, double parity) {
        this.currencyName = currencyName;
        this.parity = parity;
    }

    @Override
    public void execute() {
        Currency.getInstanceByName(currencyName).updateParity(parity);
    }
}
