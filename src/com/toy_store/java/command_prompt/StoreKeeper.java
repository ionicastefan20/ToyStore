package com.toy_store.java.command_prompt;

import java.util.ArrayList;
import java.util.List;

public class StoreKeeper {

    private static final List<Command> commands = new ArrayList<>();

    private StoreKeeper() {
        throw new IllegalStateException("Class StoreKeeper can't be instantiated");
    }

    public static void receiveCommand(Command command) {
        commands.add(command);
    }

    public static void executeCommands() {
        commands.forEach(Command::execute);
        commands.clear();
    }
}
