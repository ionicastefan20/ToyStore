package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;

public class LoadCSV implements Command {

    private final String filename;

    public LoadCSV(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute() {
        Store.getInstance().loadCSV(filename);
    }
}
