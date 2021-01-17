package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

/**
 * Command class that lists all the products present on the store.
 */
public class ListProducts implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Store.getInstance().getProducts().forEach(out::println);
    }
}
