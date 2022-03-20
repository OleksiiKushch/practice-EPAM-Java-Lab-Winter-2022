package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.ProductService;
import com.epam.task4.service.impl.ProductServiceImpl;

/**
 * @author Oleksii Kushch
 */
public class ViewProductCatalogCMD implements Command {
    public static final String FULL_KEY = "--product-list";
    public static final String SHORT_KEY = "-pl";

    public static final String DESCRIPTION = "Display a list of products";

    @Override
    public void execute() {
        ProductService productService = new ProductServiceImpl();
        productService.initRepository();
        productService.getAllProducts().forEach(product -> System.out.println(product.toStringOptional()));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
