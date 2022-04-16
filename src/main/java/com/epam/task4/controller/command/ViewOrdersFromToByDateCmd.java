package com.epam.task4.controller.command;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ViewOrdersFromToByDateCmd implements Command {
    public static final String FULL_KEY = "--order-catalog-from-to";
    public static final String SHORT_KEY = "-ocft";

    private static final String DESCRIPTION = "Display a list of orders (order catalog) for a certain period of time";

    private final OrderService orderService;

    public ViewOrdersFromToByDateCmd(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        List<Order> orders = orderService.getOrdersFromToByDate();
        if (orders.isEmpty()) {
            System.out.println(ShopLiterals.MSG_NOTHING_FOUND);
        } else {
            orders.forEach(order -> System.out.println(order.toStringWithoutId()));
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
