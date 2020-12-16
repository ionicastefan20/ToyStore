package com.toy_store.financial;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Helper {
    public static Pair<Double, Currency> convertStringToPriceCurrency (String priceWithCurrency) {
        return new ImmutablePair<>(1.0, new Currency());
    }
    static double convertPrice(String price) {
        return Double.parseDouble(price);
    }
    static double convertPrice(String price, Currency currency) {
        return 1.0;
    }
}
