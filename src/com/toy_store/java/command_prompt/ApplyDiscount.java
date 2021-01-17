package com.toy_store.java.command_prompt;

import com.toy_store.java.financial.NegativePriceException;
import com.toy_store.java.marketing.DiscountNotFoundException;
import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

/**
 * Command class that applies a discount to all the products in the store.
 */
public class ApplyDiscount implements Command {

    /**
     * The type of the discount.
     */
    private final String type;

    /**
     * The value of the discount.
     */
    private final double value;

    /**
     * Constructor of the <code>ApplyDiscount</code> class.
     * @param type the type of the discount.
     * @param value the value of the discount (will be parsed as a <code>double</code>).
     */
    public ApplyDiscount(String type, String value) {
        this.type = type;
        this.value = Double.parseDouble(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        try {
            Store.getInstance().applyDiscount(type, value);
        } catch (DiscountNotFoundException | NegativePriceException e) {
            out.println(e.getMessage());
        }
    }
}
