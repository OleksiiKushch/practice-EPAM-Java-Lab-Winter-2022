package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.mockdata.MockOrderCatalog;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ViewOrderByNearestDateCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        // TODO: validate date

        System.out.println("Please, enter the nearest date (year):");
        int year = scanner.nextInt();
        System.out.println("Please, enter the nearest date (month number):");
        int month = scanner.nextInt();
        System.out.println("Please, enter the nearest date (day):");
        int day = scanner.nextInt();
        LocalDate nearestDate = LocalDate.of(year, month, day);

        MockOrderCatalog.getInstance().getOrderCatalog().entrySet().stream().min((entry, ignored) -> // TODO: ???
                Period.between(entry.getKey().toLocalDate(), nearestDate).getDays()).stream()
                .collect(Collectors.toList()).forEach(MockOrderCatalog.getConsumeOrder());
    }


    @Override
    public String getDescription() {
        return "Displays the order by the nearest date";
    }
}
