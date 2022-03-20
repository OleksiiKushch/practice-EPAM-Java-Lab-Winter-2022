package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;
import com.epam.task4.service.impl.OrderServiceImpl;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderCatalogFromToCMD implements Command {
    public static final String FULL_KEY = "--order-list-from-to";
    public static final String SHORT_KEY = "-olft";

    public static final String DESCRIPTION = "Display a list of orders for a certain period of time";

    @Override
    public void execute() {
        OrderService orderService = new OrderServiceImpl();
        orderService.initRepository();
        List<Order> result = orderService.getOrdersFromToByDate();
        if (result != null) {
            result.forEach(System.out::println);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
