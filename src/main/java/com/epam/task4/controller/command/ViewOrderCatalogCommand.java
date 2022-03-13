package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.mockdata.MockOrderCatalog;

/**
 * @author Oleksii Kushch
 */
public class ViewOrderCatalogCommand implements Command {
    @Override
    public void execute() {
        MockOrderCatalog.getInstance().printOrderList();
    }

    @Override
    public String getDescription() {
        return "Display a list of orders";
    }
}
