package com.epam.task9.server.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Is the class that contain (hold) all server commands.
 *
 * Recommend: used a separate instance of this class (container) for each server implementation if their several.
 *
 * @author Oleksii Kushch
 */
public class ServerCommandContainer {
    private final Map<String, ServerCommand> container;

    public ServerCommandContainer() {
        container = new HashMap<>();
    }

    public void put(String commandKey, ServerCommand serverCommand) {
        container.put(commandKey, serverCommand);
    }

    public boolean isContainCommand(String commandKey) {
        return container.containsKey(commandKey);
    }

    public ServerCommand getCommandByKey(String commandKey) {
        return container.get(commandKey);
    }

}
