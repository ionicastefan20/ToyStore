package com.toy_store.financial;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;


public class Currency implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;

    private final String name;
    private final String symbol;
    private double parityToEur;
    private static final HashMap<String, Currency> allCurrencies = new HashMap<>();

    static {
        allCurrencies.put("EUR", new Currency("Euro", "EUR", 1.0));
        allCurrencies.put("USD", new Currency("US Dollar", "USD", 1.2239));
        allCurrencies.put("GBP", new Currency("Great British Pound", "GBP", 0.9061));
    }

    public static Currency getInstance() {
        return allCurrencies.get("EUR");
    }

    public static Currency getInstance(String symbol) {
        return allCurrencies.get(symbol);
    }

    private Currency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getParityToEur() {
        return parityToEur;
    }

    void updateParity(double parityToEUR) {
        this.parityToEur = parityToEUR;
    }
}
