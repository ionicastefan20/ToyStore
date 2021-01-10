package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

public class ListProducts implements Command {

    @Override
    public void execute() {
        Store.getInstance().getProducts().forEach(out::println);
    }
}
