package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderCatalogFromToCmd implements Command {
    public static final String FULL_KEY = "--order-list-from-to";
    public static final String SHORT_KEY = "-olft";

    public static final String DESCRIPTION = "Display a list of orders for a certain period of time";

    private final OrderService orderService;

    public ViewOrderCatalogFromToCmd(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        orderService.initRepository();
        List<Order> result = orderService.getOrdersFromToByDate();
        if (result != null) {
            result.forEach(order -> System.out.println(order.toStringWithoutId()));
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
