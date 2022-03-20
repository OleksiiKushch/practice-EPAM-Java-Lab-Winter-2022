package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;
import com.epam.task4.service.impl.OrderServiceImpl;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderByNearestDateCMD implements Command {
    public static final String FULL_KEY = "--order-nearest-date";
    public static final String SHORT_KEY = "-ond";

    public static final String DESCRIPTION = "Displays the order by the nearest date";

    @Override
    public void execute() {
        OrderService orderService = new OrderServiceImpl();
        orderService.initRepository();
        Order result = orderService.getOrderByNearestDate();
        if (result != null) {
            System.out.println(result);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
