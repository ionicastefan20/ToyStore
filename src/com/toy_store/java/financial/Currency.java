package com.toy_store.java.financial;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a currency. Currencies are identified by their
 * <a href="https://en.wikipedia.org/wiki/ISO_4217">ISO 4217</a> currency codes.<br>
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
     * instance.<br>
     * It is initialized with the default currency: "EUR" -> Currency{"EUR", "€", 1.0}.
     */
    private static final Map<String, Currency> currenciesMap;

    /**
     * The {@link Map Map} that maps the {@link #symbol symbol} with the {@link #name name} of the
     * currency associated instance.<br>
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

    /**
     * Getter for <code>name</code> attribute.
     * @return the name of the currency.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for <code>symbol</code> attribute.
     * @return the symbol of the currency.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Getter for parity to Euro attribute.
     * @return the parity to Euro of the currency.
     */
    public double getParityToEur() {
        return parityToEur;
    }

    /**
     * Creates a new currency with the attributes given as parameters.
     * @param name the name of the currency.
     * @param symbol the symbol of the currency.
     * @param parityToEur the parity to Euro of the currency.
     */
    public static void createInstance(String name, String symbol, double parityToEur) {
        if (currenciesMap.containsKey(name)) return;

        Currency currency = new Currency(name, symbol, parityToEur);

        currenciesMap.put(name, currency);
        symbolMap.put(symbol, name);
    }

    /**
     * Checks if the given currency exists.
     * @param name the name of the currency.
     * @return true if the currency exists, false otherwise.
     */
    public static boolean exists(String name) {
        return currenciesMap.containsKey(name);
    }

    /**
     * Gets a reference to the currency with the specified name if it exists.
     * @param name the name of the currency.
     * @return a reference to the currency with the specified name.
     * @throws CurrencyNotFoundException if the currency does not exists.
     */
    public static Currency getInstanceByName(String name) throws CurrencyNotFoundException {
        if (!currenciesMap.containsKey(name)) throw new CurrencyNotFoundException(name);

        return currenciesMap.get(name);
    }

    /**
     * Gets a reference to the currency with the specified symbol if it exists.
     * @param symbol the symbol of the currency.
     * @return a reference to the currency with the specified name.
     * @throws CurrencyNotFoundException if the currency does not exists.
     */
    public static Currency getInstanceBySymbol(String symbol) throws CurrencyNotFoundException {
        if (!symbolMap.containsKey(symbol)) throw new CurrencyNotFoundException(symbol);

        return getInstanceByName(symbolMap.get(symbol));
    }

    /**
     * Converts a price from a currency to another.
     * @param price the price that will be converted.
     * @param oldCurrency the current currency of the price.
     * @param newCurrency the new currency of the price.
     * @return the converted price.
     */
    public static double convertPrice(double price, Currency oldCurrency, Currency newCurrency) {
        return price * oldCurrency.getParityToEur() / newCurrency.getParityToEur();
    }

    /**
     * Gets a list of all currencies present on the store.
     * @return a list of all currencies
     */
    public static List<String> getAvailableCurrencies() {
        return currenciesMap.values().stream().map(Currency::toString).collect(Collectors.toList());
    }

    /**
     * Updates the parity to Euro of the currency.
     * @param parityToEUR the new parity
     */
    public void updateParity(double parityToEUR) {
        this.parityToEur = parityToEUR;
    }

    @Override
    public String toString() {
        return name + " " + parityToEur;
    }
}
