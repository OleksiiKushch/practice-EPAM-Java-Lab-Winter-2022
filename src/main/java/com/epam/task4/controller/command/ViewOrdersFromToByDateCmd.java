package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.exception.AbortOperationException;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;
import com.epam.task4.util.DateConsoleScanner;
import com.epam.task7.constants.MessageKey;

import java.time.LocalDateTime;
import java.util.List;

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
        MainApp.printMessage(MessageKey.MSG_KEY_ABILITY_CANCEL_OPERATION, ShopLiterals.BACK_CMD_FULL_CAST, ShopLiterals.BACK_CMD_SHORT_CAST);

        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = dateConsoleScanner.interactiveConsoleInputDate(ShopLiterals.MSG_ENTER_AFTER_YEAR,
                    ShopLiterals.MSG_ENTER_AFTER_MONTH, ShopLiterals.MSG_ENTER_AFTER_DAY);
            toDate = dateConsoleScanner.interactiveConsoleInputDate(ShopLiterals.MSG_ENTER_BEFORE_YEAR,
                    ShopLiterals.MSG_ENTER_BEFORE_MONTH, ShopLiterals.MSG_ENTER_BEFORE_DAY);
        } catch (AbortOperationException exception) {
            MainApp.printAlert(MessageKey.MSG_KEY_WHEN_OPERATION_ABORT);
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
