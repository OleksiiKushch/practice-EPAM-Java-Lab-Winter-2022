package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.CartHistoryService;
import com.epam.task4.service.impl.CartHistoryServiceImpl;

/**
 * @author Oleksii Kushch
 */
public class ViewLatestProductsFromCartCMD implements Command {
    public static final int QUANTITY_LAST_PRODUCTS = 5;

    public static final String FULL_KEY = "--latest-products";
    public static final String SHORT_KEY = "-lp";

    public static final String DESCRIPTION = "View information about the last " + QUANTITY_LAST_PRODUCTS
            + " product that were added to the cart in all shopping sessions";

    @Override
    public void execute() {
        CartHistoryService cartHistoryService = new CartHistoryServiceImpl();
        cartHistoryService.initRepository();
        long skip = cartHistoryService.getAllProducts().size() - QUANTITY_LAST_PRODUCTS;
        skip = skip  > 0 ? skip : 0;
        cartHistoryService.getAllProducts().stream().skip(skip)
                .forEach(product -> System.out.println(product.getFrontTitle()));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
