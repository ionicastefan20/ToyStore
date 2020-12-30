package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;

public class SaveCSV implements Command {

    private final String filename;

    public SaveCSV(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute() {
        Store.getInstance().saveCSV(filename);
    }
}
