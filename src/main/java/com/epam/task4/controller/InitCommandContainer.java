package com.epam.task4.controller;

import com.epam.task4.controller.command.CheckoutCmd;
import com.epam.task4.controller.command.HelpCmd;
import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task4.controller.command.StopCmd;
import com.epam.task4.controller.command.ViewCartCmd;
import com.epam.task4.controller.command.ViewLatestProductsFromCartCmd;
import com.epam.task4.controller.command.ViewOrderByNearestDateCmd;
import com.epam.task4.controller.command.ViewOrderCatalogCmd;
import com.epam.task4.controller.command.ViewOrdersFromToByDateCmd;
import com.epam.task4.controller.command.ViewProductCatalogCmd;
import com.epam.task4.service.CartService;
import com.epam.task4.service.OrderService;
import com.epam.task4.service.ProductService;
import com.epam.task4.service.impl.CartServiceImpl;
import com.epam.task4.service.impl.OrderServiceImpl;
import com.epam.task4.service.impl.ProductServiceImpl;

import java.util.Map;

public class InitCommandContainer {
    public static CommandContainer init(CommandContainer commandContainer) {
        initCommands(commandContainer.getCommands(), new ProductServiceImpl(), new OrderServiceImpl(), new CartServiceImpl());
        return commandContainer;
    }

    private static void initCommands(Map<String, Command> commands,
                                     ProductService productService, OrderService orderService, CartService cartService) {
        // full cast
        commands.put(ViewProductCatalogCmd.FULL_KEY, new ViewProductCatalogCmd(productService));
        commands.put(PutProductToCartCmd.FULL_KEY, new PutProductToCartCmd(cartService));
        commands.put(ViewCartCmd.FULL_KEY, new ViewCartCmd(cartService));
        commands.put(CheckoutCmd.FULL_KEY, new CheckoutCmd(cartService));
        commands.put(ViewLatestProductsFromCartCmd.FULL_KEY, new ViewLatestProductsFromCartCmd(cartService));

        commands.put(ViewOrdersFromToByDateCmd.FULL_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commands.put(ViewOrderCatalogCmd.FULL_KEY, new ViewOrderCatalogCmd(orderService));
        commands.put(ViewOrderByNearestDateCmd.FULL_KEY, new ViewOrderByNearestDateCmd(orderService));

        commands.put(HelpCmd.FULL_KEY, new HelpCmd());
        commands.put(StopCmd.FULL_KEY, new StopCmd());

        // short cast
        commands.put(ViewProductCatalogCmd.SHORT_KEY, new ViewProductCatalogCmd(productService));
        commands.put(PutProductToCartCmd.SHORT_KEY, new PutProductToCartCmd(cartService));
        commands.put(ViewLatestProductsFromCartCmd.SHORT_KEY, new ViewLatestProductsFromCartCmd(cartService));

        commands.put(ViewOrdersFromToByDateCmd.SHORT_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commands.put(ViewOrderCatalogCmd.SHORT_KEY, new ViewOrderCatalogCmd(orderService));
        commands.put(ViewOrderByNearestDateCmd.SHORT_KEY, new ViewOrderByNearestDateCmd(orderService));
    }
}
