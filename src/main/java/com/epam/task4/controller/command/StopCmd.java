package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.controller.Command;
import com.epam.task4.util.ConsoleColor;

/**
 * @author Oleksii Kushch
 */
public class StopCmd implements Command {
    public static final String FULL_KEY = "--stop";
    public static final String DESCRIPTION = "Stop the application";

    private static final String MSG_WHEN_APP_STOP = ConsoleColor.CYAN +
            "Application is stop! (shop is closing)." + ConsoleColor.RESET;

    @Override
    public void execute() {
        MainApp.stop();
        System.out.println(MSG_WHEN_APP_STOP);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
