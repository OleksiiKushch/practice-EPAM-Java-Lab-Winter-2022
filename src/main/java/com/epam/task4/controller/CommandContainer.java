package com.epam.task4.controller;

import com.epam.task4.controller.command.CheckoutCmd;
import com.epam.task4.controller.command.HelpCmd;
import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task4.controller.command.StopCmd;
import com.epam.task4.controller.command.ViewCartCmd;
import com.epam.task4.controller.command.ViewLatestProductsFromCartCmd;
import com.epam.task4.controller.command.ViewOrderByNearestDateCmd;
import com.epam.task4.controller.command.ViewOrderCatalogCmd;
import com.epam.task4.controller.command.ViewOrderCatalogFromToCmd;
import com.epam.task4.controller.command.ViewProductCatalogCmd;
import com.epam.task4.service.impl.CartServiceImpl;
import com.epam.task4.service.impl.OrderServiceImpl;
import com.epam.task4.service.impl.ProductServiceImpl;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CommandHolder is the class that initialize and contain (hold) all commands which allow
 * an application {@link com.epam.task4.MainApp}.
 * <p>
 * An associative container ({@link #commands}) that contains all commands takes a main (full) or short command cast in
 * string format as a key and an interface implementation class object Command as a value.
 * <p>
 * If you want to add a new command to the application {@link com.epam.task4.MainApp}, you should follow the steps below:
 * <ul>
 *     <li>Add to method Name statement {@code commands.put(@paramter1, @parameter2)}
 *     <ul>
 *         <li>@paramter1 - main (full) command cast in string format
 *         <li>@paramter1 - create with operator {@code new} interface implementation class object Command (pre-implement the given class with the corresponding logic)
 *     </ul>
 *     <li>(optional) If the command needs to add short command cast just repeat the previous paragraph but use the short command cast in string format as the @paramter1
 * </ul>
 *
 * @author Oleksii Kushch
 */
public class CommandContainer {
    private static final String SPACE = " ";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";

    /**
     * Is actually equal to one tabulation (4 spaces) because during the separate of the command casts in the method
     * {@link #separateCmdCasts} parentheses (i.e. 2 spaces out of 6) are substituted around the short command cast
     * (potentially several short casts).
     */
    private static final int INDENT_BETWEEN_LONGEST_CMD_AND_DESC = 6;

    private static CommandContainer instance;

    public static CommandContainer getInstance() {
        if (instance == null) {
            instance = new CommandContainer();
        }
        return instance;
    }

    private final Map<String, Command> commands;

    private CommandContainer() {
        commands = new LinkedHashMap<>();
        initCommands();
    }

    private void initCommands() {
        // full cast
        commands.put(ViewProductCatalogCmd.FULL_KEY, new ViewProductCatalogCmd(new ProductServiceImpl()));
        commands.put(PutProductToCartCmd.FULL_KEY, new PutProductToCartCmd(new CartServiceImpl()));
        commands.put(ViewCartCmd.FULL_KEY, new ViewCartCmd(new CartServiceImpl()));
        commands.put(CheckoutCmd.FULL_KEY, new CheckoutCmd(new CartServiceImpl()));
        commands.put(ViewLatestProductsFromCartCmd.FULL_KEY, new ViewLatestProductsFromCartCmd(new CartServiceImpl()));

        commands.put(ViewOrderCatalogFromToCmd.FULL_KEY, new ViewOrderCatalogFromToCmd(new OrderServiceImpl()));
        commands.put(ViewOrderCatalogCmd.FULL_KEY, new ViewOrderCatalogCmd(new OrderServiceImpl()));
        commands.put(ViewOrderByNearestDateCmd.FULL_KEY, new ViewOrderByNearestDateCmd(new OrderServiceImpl()));

        commands.put(HelpCmd.FULL_KEY, new HelpCmd());
        commands.put(StopCmd.FULL_KEY, new StopCmd());

        // short cast
        commands.put(ViewProductCatalogCmd.SHORT_KEY, new ViewProductCatalogCmd(new ProductServiceImpl()));
        commands.put(PutProductToCartCmd.SHORT_KEY, new PutProductToCartCmd(new CartServiceImpl()));
        commands.put(ViewLatestProductsFromCartCmd.SHORT_KEY, new ViewLatestProductsFromCartCmd(new CartServiceImpl()));

        commands.put(ViewOrderCatalogFromToCmd.SHORT_KEY, new ViewOrderCatalogFromToCmd(new OrderServiceImpl()));
        commands.put(ViewOrderCatalogCmd.SHORT_KEY, new ViewOrderCatalogCmd(new OrderServiceImpl()));
        commands.put(ViewOrderByNearestDateCmd.SHORT_KEY, new ViewOrderByNearestDateCmd(new OrderServiceImpl()));
    }

    /**
     * @param commandKey main (full) or short command cast in string format
     * @return true if command is exists in this class ({@link CommandContainer}) with this
     * string cast (main (full) or short command cast), and false if is not exists
     */
    public boolean isContain(String commandKey) {
        return commands.containsKey(commandKey);
    }

    /**
     * @param commandKey main (full) or short command cast in string format
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
        Integer lengthLongestCmdCasts = findLengthLongestCmdCasts();
        lengthLongestCmdCasts = lengthLongestCmdCasts != null ? lengthLongestCmdCasts : 0;

        int indentLength = lengthLongestCmdCasts + INDENT_BETWEEN_LONGEST_CMD_AND_DESC;

        commands.entrySet().stream().collect(Collectors.groupingBy(c -> c.getValue().getDescription()))
                .entrySet().stream().sorted(Comparator.comparing(e -> e.getValue().get(0).getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key, value) -> key, LinkedHashMap::new))
                .forEach((description, commands) -> {
                    String cmdCasts = commands.stream()
                            .map(Map.Entry::getKey)
                            .collect(Collectors.joining(SPACE));
                    cmdCasts = separateCmdCasts(cmdCasts);
                    int indent = indentLength - cmdCasts.length();
                    System.out.printf("%s%-" + indent + "s%s%n", cmdCasts, " ", description);
                });
    }

    /**
     * <b><i>Cmd cast</i></b> is a command call keyword, <b><i>cmd casts</i></b> is a combination of
     * all command invocation keywords separated by a space.
     * @return length of longest cmd cast
     */
    private Integer findLengthLongestCmdCasts() {
        return commands.entrySet().stream()
                .collect(Collectors.groupingBy(c -> c.getValue().getDescription()))
                .values().stream()
                .map(c -> c.stream().map(Map.Entry::getKey).collect(Collectors.joining(SPACE)).length())
                .max(Integer::compareTo).orElse(null);
    }

    /**
     * Separates non-trivial (all cast options except for the main cast (often a full cast)) command casts with parentheses.
     * @param cmdCasts space-separated command casts, example: '--product-list -pl'
     * @return all cast options except for the main surrounds with parentheses, example: '--product-list (-pl)'
     */
    private String separateCmdCasts(String cmdCasts) {
        cmdCasts = cmdCasts.replaceFirst(SPACE, SPACE + LEFT_PARENTHESIS);
        if (cmdCasts.contains(LEFT_PARENTHESIS)) {
            cmdCasts = cmdCasts.concat(RIGHT_PARENTHESIS);
        }
        return cmdCasts;
    }
}
