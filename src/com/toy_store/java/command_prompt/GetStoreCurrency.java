package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;

public class GetStoreCurrency implements Command {

    @Override
    public void execute() {
        System.out.println(Store.getInstance().getCurrency().getName());
    }
}
