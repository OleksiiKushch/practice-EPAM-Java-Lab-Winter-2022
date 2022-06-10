package com.epam.task9.server.controller;

import java.util.List;

/**
 * Is the base interface for all server commands which are mainly needs to process requests from clients.
 *
 * @author Oleksii Kushch
 */
public interface ServerCommand {
    void execute(List<String> arguments);
    Object getResult();
}
