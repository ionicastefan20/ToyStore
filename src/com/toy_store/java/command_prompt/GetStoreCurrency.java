package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import static java.lang.System.*;

public class GetStoreCurrency implements Command {

    @Override
    public void execute() {
        out.println(Store.getInstance().getCurrency());
    }
}
