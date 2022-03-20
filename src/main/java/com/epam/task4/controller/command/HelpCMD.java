package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.controller.CommandContainer;

public class HelpCMD implements Command {
    public static final String FULL_KEY = "--help";

    public static final String DESCRIPTION = "See a list of possible commands";

    @Override
    public void execute() {
        CommandContainer commandContainer = CommandContainer.getInstance();
        commandContainer.viewDescriptionAllCommands();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
