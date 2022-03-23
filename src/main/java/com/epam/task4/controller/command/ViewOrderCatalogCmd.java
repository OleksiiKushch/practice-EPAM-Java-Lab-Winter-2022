package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.OrderService;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderCatalogCmd implements Command {
    public static final String FULL_KEY = "--order-list";
    public static final String SHORT_KEY = "-ol";

    public static final String DESCRIPTION = "Display a list of orders";

    private final OrderService orderService;

    public ViewOrderCatalogCmd(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        orderService.initRepository();
        orderService.getAllOrders().forEach(order -> System.out.println(order.toStringWithoutId()));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
