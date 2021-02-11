package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;
import static java.lang.System.*;

/**
 * Command class that lists all the currencies present on the store.
 */
public class ListCurrencies implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Currency.getAvailableCurrencies().forEach(out::println);
    }
}
