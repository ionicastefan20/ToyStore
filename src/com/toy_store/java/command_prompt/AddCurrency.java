package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;

public class AddCurrency implements Command {

    private final String name;
    private final String symbol;
    private final double parityToEur;

    public AddCurrency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }

    @Override
    public void execute() {
        Store.getInstance().createCurrency(name, symbol, parityToEur);
    }
}
