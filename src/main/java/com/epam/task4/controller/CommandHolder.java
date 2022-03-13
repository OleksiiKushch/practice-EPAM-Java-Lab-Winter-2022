package com.epam.task4.controller;

import com.epam.task4.controller.command.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CommandHolder is the class that initialize and contain (hold) all commands which allow
 * an application {@link com.epam.task4.MainApp}.
 * <p>
 * An associative container ({@link #commands}) that contains all commands takes a full or short command cast in
 * string format as a key and an interface implementation class object Command as a value.
 * <p>
 * If you want to add a new command to the application {@link com.epam.task4.MainApp}, you should follow the steps below:
 * <ul>
 *     <li>Add to method Name statement {@code commands.put(@paramter1, @parameter2)}
 *     <ul>
 *         <li>@paramter1 - full command cast in string format
 *         <li>@paramter1 - create with operator {@code new} interface implementation class object Command (pre-implement the given class with the corresponding logic)
 *     </ul>
 *     <li>(optional) If the command needs to add short command cast just repeat the previous paragraph but use the short command cast in string format as the @paramter1
 * </ul>
 *
 * @author Oleksii Kushch
 */
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

    /**
     * @param commandKey full or short command cast in string format
     * @return true if command is exists in this class ({@link CommandHolder}) with this
     * string cast (full or short command cast), and false if is not exists
     */
    public boolean isContain(String commandKey) {
        return commands.containsKey(commandKey);
    }

    /**
     * @param commandKey full or short command cast in string format
     * @return object class {@link Command}
     */
    public Command getCommandByKey(String commandKey) {
        return commands.get(commandKey);
    }

    /**
     * A method that displays the description of all commands from this class ({@link CommandHolder})
     * in the appropriate format: 'full cast command' 'short cast command' command logic description
     * Output commands in sorted order according to the lexicography of the 'full cast command'.
     * <p>
     * Noted: command grouping by its description
     */
    public void viewDescriptionAllCommands() {
        commands.entrySet().stream().collect(Collectors.groupingBy(c -> c.getValue().getDescription()))
                .entrySet().stream().sorted(Comparator.comparing(e -> e.getValue().get(0).getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key, value) -> key, LinkedHashMap::new))
                .forEach((description, commands) -> {
                    commands.forEach((command) -> System.out.print("'" + command.getKey() + "' "));
                    System.out.println(description);
                });
    }
}
