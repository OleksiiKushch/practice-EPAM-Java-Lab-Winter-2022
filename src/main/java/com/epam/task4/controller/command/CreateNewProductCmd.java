package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.ProductService;

/**
 * @author Oleksii Kushch
 */
public class CreateNewProductCmd implements Command {
    public static final String FULL_KEY = "--create-new-product";
    public static final String SHORT_KEY = "-cnp";

    private static final String DESCRIPTION = "Create new product and add it's to catalog";

    private final ProductService productService;

    public CreateNewProductCmd(ProductService productService) {
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
