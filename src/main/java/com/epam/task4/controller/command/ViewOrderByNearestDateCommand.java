package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.mockdata.MockOrderCatalog;
import com.epam.task4.util.DateScanner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

public class ViewOrderByNearestDateCommand implements Command {
    @Override
    public void execute() {
        System.out.println("If you want to stop the operation type '--back' or '-b'");
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
