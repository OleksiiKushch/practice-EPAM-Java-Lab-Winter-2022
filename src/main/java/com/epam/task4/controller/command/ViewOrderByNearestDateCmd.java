package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;
import com.epam.task4.util.DateConsoleScanner;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderByNearestDateCmd implements Command {
    public static final String FULL_KEY = "--order-by-nearest-date";
    public static final String SHORT_KEY = "-obnd";

    private static final String DESCRIPTION = "Displays the order by the nearest date";

    private final OrderService orderService;

    private final DateConsoleScanner dateConsoleScanner;

    public ViewOrderByNearestDateCmd(OrderService orderService, DateConsoleScanner dateConsoleScanner) {
        this.orderService = orderService;
        this.dateConsoleScanner = dateConsoleScanner;
    }

    @Override
    public void execute() {
        MainApp.printMessage(ShopLiterals.MSG_ABILITY_CANCEL_OPERATION);

        LocalDateTime nearestDate = dateConsoleScanner.interactiveConsoleInputDate(ShopLiterals.MSG_ENTER_NEAREST_YEAR,
                ShopLiterals.MSG_ENTER_NEAREST_MONTH, ShopLiterals.MSG_ENTER_NEAREST_DAY);
        if (Objects.isNull(nearestDate)) {
            MainApp.printAlert(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return;
        }

        Order result = orderService.getOrderByNearestDate(nearestDate);
        if (Objects.isNull(result)) {
            MainApp.printAlert(StringUtils.join(ShopLiterals.MSG_NOTHING_FOUND, ShopLiterals.SPACE, ShopLiterals.MSG_ORDER_CATALOG_IS_EMPTY));
        }  else {
            System.out.println(result.toStringWithoutId());
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
