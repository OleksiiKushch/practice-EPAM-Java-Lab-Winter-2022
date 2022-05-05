package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;
import com.epam.task4.util.DateConsoleScanner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Oleksii Kushch
 */
public class ViewOrdersFromToByDateCmd implements Command {
    public static final String FULL_KEY = "--order-catalog-from-to";
    public static final String SHORT_KEY = "-ocft";

    private static final String DESCRIPTION = "Display a list of orders (order catalog) for a certain period of time";

    private final OrderService orderService;

    private final DateConsoleScanner dateConsoleScanner;

    public ViewOrdersFromToByDateCmd(OrderService orderService, DateConsoleScanner dateConsoleScanner) {
        this.orderService = orderService;
        this.dateConsoleScanner = dateConsoleScanner;
    }

    @Override
    public void execute() {
        MainApp.printMessage(ShopLiterals.MSG_ABILITY_CANCEL_OPERATION);

        LocalDateTime fromDate = dateConsoleScanner.interactiveConsoleInputDate(ShopLiterals.MSG_ENTER_AFTER_YEAR,
                ShopLiterals.MSG_ENTER_AFTER_MONTH, ShopLiterals.MSG_ENTER_AFTER_DAY);
        if (Objects.isNull(fromDate)) {
            MainApp.printAlert(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return;
        }

        LocalDateTime toDate = dateConsoleScanner.interactiveConsoleInputDate(ShopLiterals.MSG_ENTER_BEFORE_YEAR,
                ShopLiterals.MSG_ENTER_BEFORE_MONTH, ShopLiterals.MSG_ENTER_BEFORE_DAY);
        if (Objects.isNull(toDate)) {
            MainApp.printAlert(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return;
        }

        List<Order> orders = orderService.getOrdersFromToByDate(fromDate, toDate);
        if (orders.isEmpty()) {
            MainApp.printAlert(ShopLiterals.MSG_NOTHING_FOUND);
        } else {
            orders.forEach(order -> System.out.println(order.toStringWithoutId()));
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
