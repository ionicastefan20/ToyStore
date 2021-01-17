package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.ProductNotFoundException;
import static java.lang.System.*;

/**
 * Command class that shows the details of the product given as parameter.
 */
public class ShowProduct implements Command {

    /**
     * The ID of the product.
     */
    private final String id;

    /**
     * Constructor of the <code>ShowProduct</code> class.
     * @param id the ID of the product.
     */
    public ShowProduct(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        try {
            out.println(Store.getInstance().getProduct(id));
        } catch (ProductNotFoundException e) {
            out.println(e.getMessage());
        }
    }
}
