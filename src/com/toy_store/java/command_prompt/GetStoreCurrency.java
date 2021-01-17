package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

/**
 * Command class that gets the current currency of the store.
 */
public class GetStoreCurrency implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        out.println(Store.getInstance().getCurrency());
    }
}
