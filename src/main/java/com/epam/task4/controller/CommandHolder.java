package com.epam.task4.controller;

import com.epam.task4.controller.command.CheckoutCommand;
import com.epam.task4.controller.command.PutProductToCartCommand;
import com.epam.task4.controller.command.ViewCartCommand;
import com.epam.task4.controller.command.ViewLatestProductsFromCartCommand;
import com.epam.task4.controller.command.ViewOrderByNearestDateCommand;
import com.epam.task4.controller.command.ViewOrderCatalogCommand;
import com.epam.task4.controller.command.ViewOrderCatalogFromToCommand;
import com.epam.task4.controller.command.ViewProductCatalogCommand;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandHolder {
    private static CommandHolder instance;

    public static CommandHolder getInstance() {
        if (instance == null) {
            instance = new CommandHolder();
        }
        return instance;
    }

    private final Map<String, Command> commands = new LinkedHashMap<>();

    private CommandHolder() {
        initCommands();
    }

    private void initCommands() {
        // full cast
        commands.put("--product-list", new ViewProductCatalogCommand());
        commands.put("--put-to-cart", new PutProductToCartCommand());
        commands.put("--cart", new ViewCartCommand());
        commands.put("--checkout", new CheckoutCommand());
        commands.put("--latest-products", new ViewLatestProductsFromCartCommand());

        commands.put("--order-list-from-to", new ViewOrderCatalogFromToCommand());
        commands.put("--order-list", new ViewOrderCatalogCommand());
        commands.put("--order-nearest-date", new ViewOrderByNearestDateCommand());

        // short cast
        commands.put("-pl", new ViewProductCatalogCommand());
        commands.put("-ptc", new PutProductToCartCommand());
        commands.put("-lp", new ViewLatestProductsFromCartCommand());

        commands.put("-olft", new ViewOrderCatalogFromToCommand());
        commands.put("-ol", new ViewOrderCatalogCommand());
        commands.put("-ond", new ViewOrderByNearestDateCommand());
    }

    public boolean isContain(String commandKey) {
        return commands.containsKey(commandKey);
    }

    public Command getCommandByKey(String commandKey) {
        return commands.get(commandKey);
    }

    public void viewDescriptionAllCommands() {
        commands.entrySet().stream().collect(Collectors.groupingBy(c -> c.getValue().getDescription()))
                .forEach((description, commands) -> {
                    commands.forEach((command) -> System.out.print("'" + command.getKey() + "' "));
                    System.out.println(description);
                });
    }
}
