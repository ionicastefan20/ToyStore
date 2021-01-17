package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.Currency;
import com.toy_store.java.financial.CurrencyNotFoundException;
import static java.lang.System.*;

/**
 * Command class that updates the parity to Euro of the currency given as parameter.
 */
public class UpdateParity implements Command {

    /**
     * The name of the currency.
     */
    private final String currencyName;

    /**
     * The new value the parity.
     */
    private final double parity;

    /**
     *  Constructor of the <code>UpdateParity</code> class.
     * @param currencyName the name of the currency.
     * @param parity the new value the parity.
     */
    public UpdateParity(String currencyName, String parity) {
        this.currencyName = currencyName;
        this.parity = Double.parseDouble(parity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        try {
            if ("EUR".equals(currencyName)) throw new IllegalArgumentException();
            Currency.getInstanceByName(currencyName).updateParity(parity);
        } catch (CurrencyNotFoundException e) {
            out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            out.println("Exception: EUR cannot be changed!");
        }
    }
}
