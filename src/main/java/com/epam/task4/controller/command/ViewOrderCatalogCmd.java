package com.epam.task4.controller.command;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderCatalogCmd implements Command {
    public static final String FULL_KEY = "--order-list";
    public static final String SHORT_KEY = "-ol";

    private static final String DESCRIPTION = "Display a list of orders";

    private final OrderService orderService;

    public ViewOrderCatalogCmd(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println(ShopLiterals.MSG_ORDER_CATALOG_IS_EMPTY);
        } else {
            orders.forEach(order -> System.out.println(order.toStringWithoutId()));
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
