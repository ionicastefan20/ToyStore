package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import java.io.IOException;

/**
 * Command class that save a copy of the store into a binary file.
 */
public class SaveStore implements Command {

    /**
     * The name of the file.
     */
    private final String filename;

    /**
     * Constructor of the <code>SaveStore</code> class.
     * @param filename the name of the file.
     */
    public SaveStore(String filename) {
        this.filename = filename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        try {
            Store.getInstance().saveStore(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
