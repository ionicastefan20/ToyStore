package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

public class ListProductsByManufacturer implements Command {

    private final String manufacturerName;

    public ListProductsByManufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public void execute() {
        Store.getInstance().getProductsByManufacturer(manufacturerName).forEach(out::println);
    }
}
