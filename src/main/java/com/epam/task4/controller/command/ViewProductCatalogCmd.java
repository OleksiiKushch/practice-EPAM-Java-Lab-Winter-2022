package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.ProductService;

/**
 * @author Oleksii Kushch
 */
public class ViewProductCatalogCmd implements Command {
    public static final String FULL_KEY = "--product-list";
    public static final String SHORT_KEY = "-pl";

    public static final String DESCRIPTION = "Display a list of products";

    private final ProductService productService;

    public ViewProductCatalogCmd(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        productService.initRepository();
        productService.getAllProducts().forEach(product -> System.out.println(product.toStringOptional()));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
