package com.toy_store.financial;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Currency implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;

    private final String name;
    private final String symbol;
    private double parityToEur;
    private static final Map<String, Currency> currenciesMap = new HashMap<>();

    private Currency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }

    public static Currency getInstance() throws CurrencyNotFoundException {
        return getInstance("EUR");
    }

    public static Currency getInstance(String symbol) throws CurrencyNotFoundException {
        Currency currency = currenciesMap.get(symbol);
        if (currency == null) throw new CurrencyNotFoundException();
        return currency;
    }

    public static Currency createInstance(String symbol) {
        Currency currency = currenciesMap.get(symbol);
        if (currency == null) currency = new Currency("Euro", symbol, 1.0);
        return currency;
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
