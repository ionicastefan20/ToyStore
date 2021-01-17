package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;

/**
 * Command class that add a new currency to the store.
 */
public class AddCurrency implements Command {

    /**
     * The name of the currency.
     */
    private final String name;

    /**
     * The symbol of the currency.
     */
    private final String symbol;

    /**
     * It's parity to Euro.
     */
    private final double parityToEur;

    /**
     * Constructor of the <code>AddCurrency</code> class.
     * @param name the name of the currency.
     * @param symbol the symbol of the currency.
     * @param parityToEur the symbol of the currency (will be parsed as a <code>double</code>).
     */
    public AddCurrency(String name, String symbol, String parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = Double.parseDouble(parityToEur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Currency.createInstance(name, symbol, parityToEur);
    }
}
