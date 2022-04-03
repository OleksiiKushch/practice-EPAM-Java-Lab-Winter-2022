package com.epam.task4.controller.command;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderByNearestDateCmd implements Command {
    public static final String FULL_KEY = "--order-nearest-date";
    public static final String SHORT_KEY = "-ond";

    private static final String DESCRIPTION = "Displays the order by the nearest date";

    private final OrderService orderService;

    public ViewOrderByNearestDateCmd(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        Order result = orderService.getOrderByNearestDate();
        if (result == null) {
            System.out.println(ShopLiterals.MSG_NOTHING_FOUND + ShopLiterals.SPACE + ShopLiterals.MSG_ORDER_CATALOG_IS_EMPTY);
        }  else {
            System.out.println(result.toStringWithoutId());
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
