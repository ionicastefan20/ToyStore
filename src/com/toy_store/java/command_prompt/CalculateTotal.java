package com.toy_store.java.command_prompt;


import com.toy_store.java.marketing.Store;
import com.toy_store.java.utilities.PriceFormatUtility;


import java.util.Set;

import static java.lang.System.*;

public class CalculateTotal implements Command {

    private final Set<String> idList;

    public CalculateTotal(Set<String> idList) {
        this.idList = idList;
    }

    @Override
    public void execute() {
        Store store = Store.getInstance();
        out.println(PriceFormatUtility.getPriceAsString(store.calculateTotal(idList),
                store.getCurrency()));
    }
}
