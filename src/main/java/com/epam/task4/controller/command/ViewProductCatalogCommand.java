package com.epam.task4.controller.command;

import com.epam.task4.mockdata.MockProductCatalog;
import com.epam.task4.controller.Command;

public class ViewProductCatalogCommand implements Command {
    @Override
    public void execute() {
        MockProductCatalog.getInstance().printProductList();
    }

    @Override
    public String getDescription() {
        return "Display a list of products";
    }
}
