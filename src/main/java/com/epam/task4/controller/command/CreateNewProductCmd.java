package com.epam.task4.controller.command;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ConsoleColor;
import com.epam.task4.controller.Command;
import com.epam.task4.service.ProductService;
import com.epam.task7.constants.MessageKey;

import java.util.ResourceBundle;

/**
 * @author Oleksii Kushch
 */
public class CreateNewProductCmd implements Command {
    public static final String FULL_KEY = "--create-new-product";
    public static final String SHORT_KEY = "-cnp";

    private static final String DESCRIPTION = "Create new product and add it's to catalog";

    private final ProductService productService;
    private final ResourceBundle resourceBundle;

    public CreateNewProductCmd(ProductService productService, ResourceBundle resourceBundle) {
        this.productService = productService;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public void execute() {
        Commodity newProduct = productService.addProductToCatalog();
        MainApp.printSuccessMessage(MessageKey.MSG_KEY_NEW_PRODUCT_SUCCESSFULLY_CREATED);
        System.out.println(ConsoleColor.GREEN + newProduct + ConsoleColor.RESET);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
