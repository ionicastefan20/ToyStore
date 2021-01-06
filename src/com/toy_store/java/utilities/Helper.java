package com.toy_store.java.utilities;

import com.toy_store.java.financial.Currency;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class Helper {
    public static final String QUANTITY_SEPARATOR = Character.toString(160) + "| ";
    private static final Map<String, String> symbolsMap = new HashMap<>();

    private Helper() {
        throw new IllegalStateException("Utility class");
    }

    private static Pair<String, Currency> convertStringToPriceCurrency(String priceWithCurrency) {
        String symbol;
        String valueString;

        if (Character.isDigit(priceWithCurrency.charAt(0))) {
            symbol = priceWithCurrency.substring(priceWithCurrency.length() - 1);
            valueString = priceWithCurrency.substring(0, priceWithCurrency.length() - 1);
        } else {
            symbol = priceWithCurrency.substring(0, 1);
            valueString = priceWithCurrency.substring(1);
        }
        valueString = valueString.replace(",", "");

        return new ImmutablePair<>(valueString, Currency.getInstanceBySymbol(symbol));
    }

    private static double convertPrice(String price) {
        return Double.parseDouble(price);
    }

    private static double convertPrice(String price, Currency currency) {
        return convertPrice(price) * currency.getParityToEur();
    }

    public static double getPriceUtility(String priceWithCurrency, Currency storeCurrency) {
        Pair<String, Currency> pair = convertStringToPriceCurrency(priceWithCurrency);
        double valueInEUR = convertPrice(pair.getLeft(), pair.getRight());

        return valueInEUR / storeCurrency.getParityToEur();
    }

    public static String getPriceString(double price, Currency currency) {
        String result = String.format("%,.2f", price);
        String symbol = currency.getSymbol();

        if ("£".equals(symbol)) result = symbol + result;
        else result += symbol;

        return result;
    }
}
