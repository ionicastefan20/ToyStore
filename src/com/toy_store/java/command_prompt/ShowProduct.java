package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

public class ShowProduct implements Command {

    private final String uniqueId;

    public ShowProduct(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public void execute() {
        out.print(Store.getInstance().getProduct(uniqueId));
    }
}
