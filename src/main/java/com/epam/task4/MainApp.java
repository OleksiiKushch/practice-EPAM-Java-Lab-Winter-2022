package com.epam.task4;

import com.epam.task4.constants.ConsoleColor;
import com.epam.task4.constants.ShopLiterals;

/**
 * Class implementation the console user-interface for business logic "Online store".
 *
 * @author Oleksii Kushch
 */
public class MainApp {
    private static boolean isRunning;
    private static AppContext context;

    /** main interact method */
    public static void run() {
        isRunning = true;
        setContext(new AppContext());
        System.out.println(ShopLiterals.MSG_WHEN_APP_RUN);
        while (isRunning) {
            String command = context.getScanner().nextLine().strip();
            if (context.getCommandContainer().isContainCommand(command)) {
                context.getCommandContainer().getCommandByKey(command).execute();
            } else if (!command.isBlank()) {
                System.out.println(ShopLiterals.MSG_UNSUPPORTED_COMMAND);
            }
        }
        context.getScanner().close();
    }

    public static void stop() {
        isRunning = false;
    }

    private static void setContext(AppContext context) {
        MainApp.context = context;
    }

    public static AppContext getContext() {
        return context;
    }

    public static void printMessage(String messageKey, Object... args) {
        printMsg(messageKey, ConsoleColor.CYAN, args);
    }

    public static void printSuccessMessage(String messageKey, Object... args) {
        printMsg(messageKey, ConsoleColor.GREEN, args);
    }

    public static void printAlert(String messageKey, Object... args) {
        printMsg(messageKey, ConsoleColor.YELLOW, args);
    }

    public static void printWarning(String messageKey, Object... args) {
        printMsg(messageKey, ConsoleColor.RED, args);
    }

    private static void printMsg(String messageKey, String ansiCodeColor, Object... args) {
        System.out.printf(ansiCodeColor + context.getResourceBundle().getString(messageKey)
                + ConsoleColor.RESET, args);
    }

    public static void main(String[] args) {
        MainApp.run();
    }
}
