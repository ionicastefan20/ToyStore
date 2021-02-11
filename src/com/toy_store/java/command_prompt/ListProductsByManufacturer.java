package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.ManufacturerNotFoundException;

import static java.lang.System.*;

/**
 * Command class that lists all the products present on the store from the manufacturer given as
 * parameter.
 */
public class ListProductsByManufacturer implements Command {

    /**
     * The name of the manufacturer.
     */
    private final String manufacturerName;

    /**
     * Constructor of the <code>ListProductsByManufacturer</code> class.
     * @param manufacturerName the name of the manufacturer.
     */
    public ListProductsByManufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        try {
            Store.getInstance().getProductsByManufacturer(manufacturerName).forEach(out::println);
        } catch (ManufacturerNotFoundException e) {
            out.println(e.getMessage()) ;
        }
    }
}
