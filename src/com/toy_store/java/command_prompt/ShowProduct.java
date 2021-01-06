package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;

public class ShowProduct implements Command {

    private final String uniqueId;

    public ShowProduct(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public void execute() {
        Store.getInstance().getProduct(uniqueId);
    }
}
