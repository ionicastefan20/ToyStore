package com.toy_store.java.utilities;

import com.toy_store.java.financial.Currency;
import com.toy_store.java.financial.CurrencyNotFoundException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import static java.lang.System.*;

/**
 * Utility class that processes Strings containing prices.
 */
public class PriceFormatUtility {

    /**
     * The class cannot be instantiated. It's an utility class.
     */
    private PriceFormatUtility() {
        throw new IllegalStateException("Utility class cannot be instantiated.");
    }

    /**
     * Converts a string containing a price into a pair with the value (double) and the Currency
     * associated with it.
     * @param priceWithCurrency the string to be processed
     * @return the pair explained above
     * @throws CurrencyNotFoundException if the currency does not exist
     */
    private static Pair<Double, Currency> convertStringToPriceCurrency(String priceWithCurrency)
            throws CurrencyNotFoundException {
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

        return new ImmutablePair<>(Double.parseDouble(valueString), Currency.getInstanceBySymbol(symbol));
    }

    /**
     * Wrapper for the <code>convertStringToPriceCurrency(String)</code> function. It converts the
     * price to the currency of the store.
     * @param priceWithCurrency the string containing the price
     * @param storeCurrency the currency of the store
     * @return the price as double
     */
    public static double getPriceFromString(String priceWithCurrency, Currency storeCurrency) {
        Pair<Double, Currency> pair = null;
        try {
            pair = convertStringToPriceCurrency(priceWithCurrency);
        }  catch (CurrencyNotFoundException e) {
            out.println(e.getMessage() + " Exiting...");
            exit(1);
        }

        return Currency.convertPrice(pair.getLeft(), pair.getRight(), storeCurrency);
    }

    /**
     * Converts a price from the store to its String form to be printed or added to the CSV file.
     * @param price the price to be converted
     * @param currency the currency of the price
     * @return the resulting String
     */
    public static String getPriceAsString(double price, Currency currency) {
        return currency.getSymbol() + String.format("%,.3f", price);
    }
}
