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
public class CommandContainer {
    private static CommandContainer instance;

    public static CommandContainer getInstance() {
        if (instance == null) {
            instance = new CommandContainer();
        }
        return instance;
    }

    private final Map<String, Command> commands = new LinkedHashMap<>();

    private CommandContainer() {
        initCommands();
    }

    private void initCommands() {
        // full cast
        commands.put(ViewProductCatalogCMD.FULL_KEY, new ViewProductCatalogCMD());
        commands.put(PutProductToCartCMD.FULL_KEY, new PutProductToCartCMD());
        commands.put(ViewCartCMD.FULL_KEY, new ViewCartCMD());
        commands.put(CheckoutCMD.FULL_KEY, new CheckoutCMD());
        commands.put(ViewLatestProductsFromCartCMD.FULL_KEY, new ViewLatestProductsFromCartCMD());

        commands.put(ViewOrderCatalogFromToCMD.FULL_KEY, new ViewOrderCatalogFromToCMD());
        commands.put(ViewOrderCatalogCMD.FULL_KEY, new ViewOrderCatalogCMD());
        commands.put(ViewOrderByNearestDateCMD.FULL_KEY, new ViewOrderByNearestDateCMD());

        commands.put(HelpCMD.FULL_KEY, new HelpCMD());
        commands.put(StopCMD.FULL_KEY, new StopCMD());

        // short cast
        commands.put(ViewProductCatalogCMD.SHORT_KEY, new ViewProductCatalogCMD());
        commands.put(PutProductToCartCMD.SHORT_KEY, new PutProductToCartCMD());
        commands.put(ViewLatestProductsFromCartCMD.SHORT_KEY, new ViewLatestProductsFromCartCMD());

        commands.put(ViewOrderCatalogFromToCMD.SHORT_KEY, new ViewOrderCatalogFromToCMD());
        commands.put(ViewOrderCatalogCMD.SHORT_KEY, new ViewOrderCatalogCMD());
        commands.put(ViewOrderByNearestDateCMD.SHORT_KEY, new ViewOrderByNearestDateCMD());
    }

    /**
     * @param commandKey full or short command cast in string format
     * @return true if command is exists in this class ({@link CommandContainer}) with this
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
     * A method that displays the description of all commands from this class ({@link CommandContainer})
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
