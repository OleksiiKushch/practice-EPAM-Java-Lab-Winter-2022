package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.mockdata.MockOrderCatalog;
import com.epam.task4.util.DateScanner;

import java.time.LocalDateTime;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderCatalogFromToCommand implements Command {
    /**
     * The interactive part of the operation execution consists of six parts:
     * <ul>
     *     <li> Entering the year (start-point) from the console
     *     <li> Entering the month (month number) (start-point) from the console
     *     <li> Entering the day (start-point) from the console
     *     <li> Entering the year from (end-point) the console
     *     <li> Entering the month (month number) (end-point) from the console
     *     <li> Entering the day (end-point) from the console
     * </ul>
     * On any of these six parts, it is possible to stop execution of all operation if the corresponding methods
     * for reading data from the console ({@link DateScanner#inputYear}, {@link DateScanner#inputMonth},
     * {@link DateScanner#inputDay}) return {@code null}.
     */
    @Override
    public void execute() {
        System.out.println("If you want to stop (abort) the operation type '--back' or '-b'");

        System.out.println("Please, enter FROM (after) date (year):");
        Integer fromYear = DateScanner.inputYear();
        if (fromYear == null) { return; }   // abort the entire operation
        System.out.println("Please, enter FROM (after) date (month number):");
        Integer fromMonth = DateScanner.inputMonth(fromYear);
        if (fromMonth == null) { return; }   // abort the entire operation
        System.out.println("Please, enter FROM (after) date (day):");
        Integer fromDay = DateScanner.inputDay(fromYear, fromMonth);
        if (fromDay == null) { return; }   // abort the entire operation
        LocalDateTime fromDate = LocalDateTime.of(fromYear, fromMonth, fromDay, 0, 0, 0);

        System.out.println("Please, enter TO (before) date (year):");
        Integer toYear = DateScanner.inputYear();
        if (toYear == null) { return; }   // abort the entire operation
        System.out.println("Please, enter TO (before) date (month number):");
        Integer toMonth = DateScanner.inputMonth(toYear);
        if (toMonth == null) { return; }   // abort the entire operation
        System.out.println("Please, enter TO (before) date (day):");
        Integer toDay = DateScanner.inputDay(toYear, toMonth);
        if (toDay == null) { return; }   // abort the entire operation
        LocalDateTime toDate = LocalDateTime.of(toYear, toMonth, toDay, 0, 0, 0);

        MockOrderCatalog.getInstance().getOrderCatalog().entrySet().stream().filter(entry ->
                entry.getKey().isAfter(fromDate) && entry.getKey().isBefore(toDate))
                .forEach(MockOrderCatalog.getConsumeOrder());
    }

    @Override
    public String getDescription() {
        return "Display a list of orders for a certain period of time";
    }
}
