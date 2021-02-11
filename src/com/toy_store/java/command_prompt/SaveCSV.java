package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;

/**
 * Command class that save the data present in the store into  a CSV file.
 */
public class SaveCSV implements Command {

    /**
     * The name of the file.
     */
    private final String filename;

    /**
     * Constructor of the <code>SaveCSV</code> class.
     * @param filename the name of the file.
     */
    public SaveCSV(String filename) {
        this.filename = filename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Store.getInstance().saveCSV(filename);
    }
}
