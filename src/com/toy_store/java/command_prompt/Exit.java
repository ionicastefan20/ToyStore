package com.toy_store.java.command_prompt;

/**
 * Command class that exits the store.
 */
public class Exit implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        System.exit(0);
    }
}
