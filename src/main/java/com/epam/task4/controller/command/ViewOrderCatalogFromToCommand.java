package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.mockdata.MockOrderCatalog;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ViewOrderCatalogFromToCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        // TODO: validate date

        System.out.println("Please, enter FROM (after) date (year):");
        int fromYear = scanner.nextInt();
        System.out.println("Please, enter FROM (after) date (month number):");
        int fromMonth = scanner.nextInt();
        System.out.println("Please, enter FROM (after) date (day):");
        int fromDay = scanner.nextInt();
        LocalDateTime toDate = LocalDateTime.of(fromYear, fromMonth, fromDay, 0, 0, 0);

        System.out.println("Please, enter TO (before) date (year):");
        int toYear = scanner.nextInt();
        System.out.println("Please, enter TO (before) date (month number):");
        int toMonth = scanner.nextInt();
        System.out.println("Please, enter TO (before) date (day):");
        int toDay = scanner.nextInt();
        LocalDateTime fromDate = LocalDateTime.of(toYear, toMonth, toDay, 0, 0, 0);

        MockOrderCatalog.getInstance().getOrderCatalog().entrySet().stream().filter(entry ->
                entry.getKey().isAfter(toDate) && entry.getKey().isBefore(fromDate)).forEach(MockOrderCatalog.getConsumeOrder());
    }

    @Override
    public String getDescription() {
        return "Display a list of orders for a certain period of time";
    }
}
