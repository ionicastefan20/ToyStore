package com.toy_store.financial;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Helper {
    public static final String QUANTITY_SEPARATOR = Character.toString(194) + Character.toString(160);

    private Helper() {
        throw new IllegalStateException("Utility class");
    }

    public static Pair<Double, Currency> convertStringToPriceCurrency(String priceWithCurrency) {
        if (priceWithCurrency.length() == 0) {
            return new ImmutablePair<>(0.0, Currency.getInstance());
        } else {
            priceWithCurrency = priceWithCurrency.substring(1);

            String symbol;
            String value;
            if (Character.isDigit(priceWithCurrency.charAt(0))) {
                symbol = priceWithCurrency.substring(priceWithCurrency.length()-1);
                value = priceWithCurrency.substring(0, priceWithCurrency.length()-1);
            } else {
                symbol = priceWithCurrency.substring(0, 1);
                value = priceWithCurrency.substring(1);
            }
            return new ImmutablePair<>(convertPrice(value), Currency.getInstance(symbol));
        }
    }

    static double convertPrice(String price) {
        return Double.parseDouble(price);
    }

    static double convertPrice(String price, Currency currency) {
        return convertPrice(price) * currency.getParityToEur();
    }
}
