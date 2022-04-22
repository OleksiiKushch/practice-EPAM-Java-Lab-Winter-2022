package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.controller.Command;

/**
 * @author Oleksii Kushch
 */
public class HelpCmd implements Command {
    public static final String FULL_KEY = "--help";

    private static final String DESCRIPTION = "Display a list of possible commands";

    @Override
    public void execute() {
        MainApp.getContext().getCommandContainer().viewDescriptionAllCommands();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
