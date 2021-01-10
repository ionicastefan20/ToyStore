package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;

public class AddCurrency implements Command {

    private final String name;
    private final String symbol;
    private final double parityToEur;

    public AddCurrency(String name, String symbol, String parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = Double.parseDouble(parityToEur);
    }

    @Override
    public void execute() {
        Currency.createInstance(name, symbol, parityToEur);
    }
}
