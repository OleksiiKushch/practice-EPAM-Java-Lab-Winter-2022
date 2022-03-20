package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.OrderService;
import com.epam.task4.service.impl.OrderServiceImpl;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderCatalogCMD implements Command {
    public static final String FULL_KEY = "--order-list";
    public static final String SHORT_KEY = "-ol";

    public static final String DESCRIPTION = "Display a list of orders";

    @Override
    public void execute() {
        OrderService orderService = new OrderServiceImpl();
        orderService.initRepository();
        orderService.getAllOrders().forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
