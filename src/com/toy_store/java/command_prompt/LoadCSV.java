package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;

/**
 * Command class that loads the data from a CSV file into the store.
 */
public class LoadCSV implements Command {

    /**
     * The name of the file.
     */
    private final String filename;

    /**
     * Constructor of the <code>LoadCSV</code> class.
     * @param filename the name of the file.
     */
    public LoadCSV(String filename) {
        this.filename = filename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Store.getInstance().loadCSV(filename);
    }
}
