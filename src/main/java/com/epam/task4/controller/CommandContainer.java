package com.epam.task4.controller;

import com.epam.task4.constants.ShopLiterals;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CommandHolder is the class that contain (hold) all commands which allow an application {@link com.epam.task4.MainApp}.
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
    /**
     * Is actually equal to one tabulation (4 spaces) because during the separate of the command casts in the method
     * {@link #separateCmdCasts} parentheses (i.e. 2 spaces out of 6) are substituted around the short command cast
     * (potentially several short casts).
     */
    private static final int INDENT_BETWEEN_LONGEST_CMD_AND_DESC = 6;

    private final Map<String, Command> commands;

    public CommandContainer() {
        commands = new LinkedHashMap<>();
    }

    public void put(String strCommand, Command command) {
        commands.put(strCommand, command);
    }

    /**
     * @param commandKey main (full) or short command cast in string format
     * @return true if command is exists in this class ({@link CommandContainer}) with this
     * string cast (main (full) or short command cast), and false if is not exists
     */
    public boolean isContainCommand(String commandKey) {
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
                            .collect(Collectors.joining(ShopLiterals.SPACE));
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
                .map(c -> c.stream().map(Map.Entry::getKey).collect(Collectors.joining(ShopLiterals.SPACE)).length())
                .max(Integer::compareTo).orElse(null);
    }

    /**
     * Separates non-trivial (all cast options except for the main cast (often a full cast)) command casts with parentheses.
     * @param cmdCasts space-separated command casts, example: '--product-list -pl'
     * @return all cast options except for the main surrounds with parentheses, example: '--product-list (-pl)'
     */
    private String separateCmdCasts(String cmdCasts) {
        cmdCasts = cmdCasts.replaceFirst(ShopLiterals.SPACE, ShopLiterals.SPACE + ShopLiterals.LEFT_PARENTHESIS);
        if (cmdCasts.contains(ShopLiterals.LEFT_PARENTHESIS)) {
            cmdCasts = cmdCasts.concat(ShopLiterals.RIGHT_PARENTHESIS);
        }
        return cmdCasts;
    }
}
