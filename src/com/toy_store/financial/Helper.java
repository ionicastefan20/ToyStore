package com.toy_store.financial;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class Helper {
    public static final String QUANTITY_SEPARATOR = Character.toString(194) + Character.toString(160);
    private static final Map<String, String> symbolsMap = new HashMap<>();

    static {
        symbolsMap.put("$", "USD");
        symbolsMap.put("£", "GBP");
        symbolsMap.put("€", "EUR");
    }

    private Helper() {
        throw new IllegalStateException("Utility class");
    }

    public static Pair<Double, Currency> convertStringToPriceCurrency(String priceWithCurrency) {
        String symbol;
        String value;

        if (Character.isDigit(priceWithCurrency.charAt(0))) {
            symbol = priceWithCurrency.substring(priceWithCurrency.length() - 1);
            value = priceWithCurrency.substring(0, priceWithCurrency.length() - 1);
        } else {
            symbol = priceWithCurrency.substring(0, 1);
            value = priceWithCurrency.substring(1);
        }
        symbol = symbolsMap.get(symbol);

        Pair<Double, Currency> pair = null;
        try {
            pair = new ImmutablePair<>(convertPrice(value), Currency.getInstance(symbol));
        } catch (CurrencyNotFoundException e) {
            e.printStackTrace();
        }
        return pair;
    }

    static double convertPrice(String price) {
        return Double.parseDouble(price);
    }

    static double convertPrice(String price, Currency currency) {
        return convertPrice(price) * currency.getParityToEur();
    }
}
