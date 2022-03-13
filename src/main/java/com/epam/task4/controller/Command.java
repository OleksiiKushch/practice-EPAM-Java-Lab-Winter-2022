package com.epam.task4.controller;

/**
 * Command is the base interface for all commands which allow an application
 * {@link com.epam.task4.MainApp}.
 *
 * @author Oleksii Kushch
 */
public interface Command {
    /**
     * The base method of all commands that is executed according to the supplied logic
     * for the corresponding command.
     */
    void execute();

    /**
     * Method describing the the supplied logic for the corresponding command.
     * @return description of the executed logic of the corresponding command
     */
    String getDescription();
}
