package com.toy_store.java.command_prompt;

public class Exit implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }
}
