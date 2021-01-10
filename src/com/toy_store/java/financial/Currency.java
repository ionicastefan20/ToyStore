package com.toy_store.java.financial;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a currency. Currencies are identified by their
 * <a href="https://en.wikipedia.org/wiki/ISO_4217">ISO 4217</a> currency codes.
 * <p>
 * The class is designed so that all <code>Currency</code> instances are saved in a
 * {@link Map Map}, so there are no duplicates. Therefore, there is no public constructor.
 * A new instance can be obtained by using the {@link #createInstance
 * createInstance(String, String, double)} method or you can get an existing instance by using the
 * {@link #getInstanceByName getInstanceByName(String)} or {@link #getInstanceBySymbol
 * getInstanceBySymbol(String)} method.
 *
 * @author Stefan-Theodor Ionica (ionicastefan20@gmail.com)
 */
public class Currency implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;

    /**
     * The ISO 4217 currency code (e.g. "EUR", "USD") associated with this <code>Currency</code>
     * instance.
     *
     * @see #getName()
     */
    private final String name;

    /**
     * The symbol (e.g. "€", "$") associated with this <code>Currency</code> instance.
     *
     * @see #getSymbol()
     */
    private final String symbol;

    /**
     * The exchange rate against the Euro associated with this <code>Currency</code> instance.
     *
     * @see #getParityToEur()
     * @see #updateParity(double)
     */
    private double parityToEur;

    /**
     * The {@link Map Map} that maps the {@link #name name} of the currency with its associated
     * instance.
     * <p>
     * It is initialized with the default currency: "EUR" -> Currency{"EUR", "€", 1.0}.
     */
    private static final Map<String, Currency> currenciesMap;

    /**
     * The {@link Map Map} that maps the {@link #symbol symbol} with the {@link #name name} of the
     * currency associated instance.
     * <p>
     * It is initialized with the default currency: "€" -> "EUR".
     */
    private static final Map<String, String> symbolMap;

    static {
        currenciesMap = new HashMap<>();
        currenciesMap.put("EUR", new Currency("EUR", "€", 1.0));

        symbolMap = new HashMap<>();
        symbolMap.put("€", "EUR");
    }

    /**
     * Private constructor used by the {@link #createInstance
     * createInstance(String, String, double)} method to create a new <code>Currency</code>
     * instance.
     *
     * @param name the ISO 4217 currency code associated with the currency
     * @param symbol the symbol associated with the currency
     * @param parityToEur the exchange rate against the Euro associated with the currency
     */
    private Currency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
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

    public static void createInstance(String name, String symbol, double parityToEur) {
        if (!currenciesMap.containsKey(name)) return;

        Currency currency = new Currency(name, symbol, parityToEur);

        currenciesMap.put(name, currency);
        symbolMap.put(symbol, name);
    }

    public static boolean exists(String name) {
        return currenciesMap.containsKey(name);
    }

    public static Currency getInstanceByName(String name) {
        return currenciesMap.get(name);
    }

    public static Currency getInstanceBySymbol(String symbol) {
        return getInstanceByName(symbolMap.get(symbol));
    }

    public static List<String> getAvailableCurrencies() {
        return currenciesMap.values().stream().map(Currency::toString).collect(Collectors.toList());
    }

    public void updateParity(double parityToEUR) {
        this.parityToEur = parityToEUR;
    }

    @Override
    public String toString() {
        return name + " " + parityToEur;
    }
}
