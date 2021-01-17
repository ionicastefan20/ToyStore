package com.toy_store.java.command_prompt;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that keeps track of all commands and executes them at once.<br>
 * The class cannot be instantiated.
 */
public class StoreKeeper {

    /**
     * The <code>List</code> of commands.
     */
    private static final List<Command> commands = new ArrayList<>();

    /**
     * Private constructor of the <code>StoreKeeper</code> class.<br>
     * Any attempt of instantiation will result in an exception: <code>IllegalStateException</code>.
     */
    private StoreKeeper() {
        throw new IllegalStateException("Utility class cannot be instantiated.");
    }

    /**
     * Receives a <code>Command</code> and adds it to the list.
     * @param command the command that needs to be added to the list.
     */
    public static void receiveCommand(Command command) {
//        commands.add(command);
        command.execute();
    }

    /**
     * Executes all commands present in the list.
     */
    public static void executeCommands() {
        commands.forEach(Command::execute);
        commands.clear();
    }
}
