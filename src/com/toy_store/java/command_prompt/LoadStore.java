package com.toy_store.java.command_prompt;

import com.toy_store.java.marketing.Store;
import java.io.IOException;

/**
 * Command class that loads an older state of the store from a binary file.
 */
public class LoadStore implements Command {

    /**
     * The name of the file.
     */
    private final String filename;

    /**
     * Constructor of the <code>LoadStore</code> class.
     * @param filename the name of the file.
     */
    public LoadStore(String filename) {
        this.filename = filename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        try {
            Store.loadStore(filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
