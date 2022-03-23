package com.epam.task4;

import com.epam.task4.controller.CommandContainer;
import com.epam.task4.util.ConsoleColor;

import java.util.Scanner;

/**
 * Class implementation the console user-interface for business logic "Online store".
 *
 * @author Oleksii Kushch
 */
public class MainApp {
    private static Scanner scanner;
    private static boolean isRunning;

    private static final String MSG_WHEN_APP_RUN = ConsoleColor.CYAN + "Application is start! (shop is opening).\n" +
            "Enter '--help' to see a list of possible commands, or '--stop' to stop the application." + ConsoleColor.RESET;
    private static final String MSG_UNSUPPORTED_COMMAND = ConsoleColor.RED + "Unsupported command" + ConsoleColor.RESET;

    /** main interact method */
    public static void run() {
        initAppContext();
        System.out.println(MSG_WHEN_APP_RUN);
        while (isRunning) {
            String command = scanner.nextLine().trim();
            if (CommandContainer.getInstance().isContain(command)) {
                CommandContainer.getInstance().getCommandByKey(command).execute();
            } else if (command.isBlank()) {
                // do nothing if the command entered is empty or contains only white space codepoints
                // was mostly added due to accidental pressing 'Enter'
            } else {
                System.out.println(MSG_UNSUPPORTED_COMMAND);
            }
        }
        scanner.close();
    }

    private static void initAppContext() {
        new AppContext().init();
        scanner = new Scanner(System.in);
        isRunning = true;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void stop() {
        isRunning = false;
    }

    public static void main(String[] args) {
        MainApp.run();
    }
}
