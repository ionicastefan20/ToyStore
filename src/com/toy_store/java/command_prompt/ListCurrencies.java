package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;
import static java.lang.System.*;

public class ListCurrencies implements Command {

    @Override
    public void execute() {
        Currency.getAvailableCurrencies().forEach(out::println);
    }
}
