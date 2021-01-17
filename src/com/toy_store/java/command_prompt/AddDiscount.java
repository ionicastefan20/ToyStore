package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.DiscountType;
import com.toy_store.java.marketing.Store;

/**
 * Command class that add a new discount to the store.
 */
public class AddDiscount implements Command {

    /**
     * The type of the discount.
     */
    private final DiscountType discountType;

    /**
     * The value of the discount.
     */
    private final double value;

    /**
     * The name of the discount.
     */
    private final String name;

    /**
     * Constructor of the <code>AddDiscount</code> class.
     * @param discountTypeStr the type of the discount.
     * @param value the value of the discount (will be parsed as a <code>double</code>).
     * @param name the name of the discount.
     */
    public AddDiscount(String discountTypeStr, String value, String name) {
        this.discountType = DiscountType.valueOf(discountTypeStr + "_DISCOUNT");
        this.value = Double.parseDouble(value);
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Store.getInstance().createDiscount(name, discountType, value);
    }
}
