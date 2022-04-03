package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.ProductService;

/**
 * @author Oleksii Kushch
 */
public class AddProductToCatalogCmd implements Command {
    public static final String FULL_KEY = "--add-product-to-catalog";
    public static final String SHORT_KEY = "-aptc";

    private static final String DESCRIPTION = "Add product to catalog";

    private final ProductService productService;

    public AddProductToCatalogCmd(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        productService.addProductToCatalog();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
