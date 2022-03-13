package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.mockdata.MockOrderCatalog;
import com.epam.task4.util.DateScanner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderByNearestDateCommand implements Command {
    /**
     * Noted: The interactive part of the operation execution consists of three parts:
     * <ul>
     *     <li> Entering the year from the console
     *     <li> Entering the month (month number) from the console
     *     <li> Entering the day from the console
     * </ul>
     * On any of these three parts, it is possible to stop execution of all operation (this method execute())
     * if the corresponding methods for reading data from the console ({@link DateScanner#inputYear}, {@link DateScanner#inputMonth},
     * {@link DateScanner#inputDay}) return {@code null}.
     */
    @Override
    public void execute() {
        System.out.println("If you want to stop (abort) the operation type '--back' or '-b'");

        System.out.println("Please, enter the nearest date (year):");
        Integer year = DateScanner.inputYear();
        if (year == null) { return; }   // abort the entire operation
        System.out.println("Please, enter the nearest date (month number):");
        Integer month = DateScanner.inputMonth(year);
        if (month == null) { return; }   // abort the entire operation
        System.out.println("Please, enter the nearest date (day):");
        Integer day = DateScanner.inputDay(year, month);
        if (day == null) { return; }   // abort the entire operation

        LocalDateTime nearestDate = LocalDateTime.of(year, month, day, 0, 0, 0);

        MockOrderCatalog.getInstance().getOrderCatalog().entrySet().stream().min(Comparator.comparing(
                entry -> Math.abs(Duration.between(entry.getKey(), nearestDate).toDays())))
                .ifPresent(MockOrderCatalog.getConsumeOrder());
    }

    @Override
    public String getDescription() {
        return "Displays the order by the nearest date";
    }
}
