package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import java.util.List;
import static java.lang.System.*;

/**
 * Command class that lists all the discounts present on the store.
 */
public class ListDiscounts implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        List<String> aux = Store.getInstance().getDiscounts();
        aux.forEach(out::println);
    }
}
