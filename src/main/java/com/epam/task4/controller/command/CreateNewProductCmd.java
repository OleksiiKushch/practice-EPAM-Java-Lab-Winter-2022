package com.epam.task4.controller.command;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.exception.AbortOperationException;
import com.epam.task4.service.ProductService;
import com.epam.task6.util.ProductDataConsoleScanner;
import com.epam.task7.constants.MessageKey;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Oleksii Kushch
 */
public class CreateNewProductCmd implements Command {
    public static final String FULL_KEY = "--create-new-product";
    public static final String SHORT_KEY = "-cnp";

    private static final String DESCRIPTION = "Create new product and add it's to catalog";

    private final ProductService productService;

    private final ProductDataConsoleScanner productDataConsoleScanner;

    public CreateNewProductCmd(ProductService productService, ProductDataConsoleScanner productDataConsoleScanner) {
        this.productService = productService;
        this.productDataConsoleScanner = productDataConsoleScanner;
    }

    @Override
    public void execute() {
        MainApp.printMessage(MessageKey.MSG_KEY_ABILITY_CANCEL_OPERATION, ShopLiterals.BACK_CMD_FULL_CAST, ShopLiterals.BACK_CMD_SHORT_CAST);

        Commodity newProduct;
        try {
            newProduct = productDataConsoleScanner.inputCreateProductType().create();
        } catch (AbortOperationException exception) {
            MainApp.printAlert(MessageKey.MSG_KEY_WHEN_OPERATION_ABORT);
            return;
        }

        productService.addProductToCatalog(newProduct);
        MainApp.printSuccessMessage(MessageKey.MSG_KEY_NEW_PRODUCT_SUCCESSFULLY_CREATED);
        MainApp.printSuccessMessage(StringUtils.join(newProduct.userFriendlyToString(MainApp.getContext().getResourceBundle())));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
