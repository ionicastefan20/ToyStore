package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.Manufacturer;

public class ListManufacturer implements Command {

    @Override
    public void execute() {
        for (Manufacturer manufacturer : Store.getInstance().getManufacturers()) {
            System.out.println(manufacturer);
        }
    }
}
