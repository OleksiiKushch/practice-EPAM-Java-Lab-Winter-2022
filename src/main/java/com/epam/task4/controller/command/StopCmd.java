package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;

/**
 * @author Oleksii Kushch
 */
public class StopCmd implements Command {
    public static final String FULL_KEY = "--stop";
    public static final String DESCRIPTION = "Stop the application";

    @Override
    public void execute() {
        MainApp.stop();
        System.out.println(ShopLiterals.MSG_WHEN_APP_STOP);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
