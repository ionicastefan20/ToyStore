package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.CurrencyNotFoundException;
import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

/**
 * Command class that changes the current currency of the store.
 */
public class SetStoreCurrency implements Command {

    /**
     * The name of the currency.
     */
    private final String currencyName;

    /**
     * Constructor of the <code>SetStoreCurrency</code> class.
     * @param currencyName the name of the currency.
     */
    public SetStoreCurrency(String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        try {
            Store.getInstance().changeCurrency(currencyName);
        } catch (CurrencyNotFoundException e) {
            out.println(e.getMessage());
        }
    }
}
