package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.controller.Command;

public class StopCMD implements Command {
    public static final String FULL_KEY = "--stop";
    public static final String DESCRIPTION = "Stop the application";

    @Override
    public void execute() {
        MainApp.stop();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
