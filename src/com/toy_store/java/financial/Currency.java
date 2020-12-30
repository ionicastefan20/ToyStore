package com.toy_store.java.financial;

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
    private static final Map<String, String> symbolMap = new HashMap<>();

    static {
        currenciesMap.put("EUR", new Currency("EUR", "€", 1.0));
        symbolMap.put("€", "EUR");
    }

    private Currency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }

    public static Currency getInstanceByName() {
        return getInstanceByName("EUR");
    }

    public static Currency getInstanceByName(String name) {
        return currenciesMap.get(name);
    }

    public static Currency getInstanceBySymbol(String symbol) {
        return getInstanceByName(symbolMap.get(symbol));
    }

    public static Currency createInstance(String name, String symbol, double parityToEur) {
        Currency currency = new Currency(name, symbol, parityToEur);

        currenciesMap.put(name, currency);
        symbolMap.put(symbol, name);

        return currency;
    }

    public static boolean exists(String symbol) {
        return currenciesMap.containsKey(symbol);
    }

    public String getName() {
        return name;
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

    public static Currency[] getAvailableCurrencies() {
        return currenciesMap.values().toArray(new Currency[0]);
    }

    @Override
    public String toString() {
        return name + " " + parityToEur;
    }
}
