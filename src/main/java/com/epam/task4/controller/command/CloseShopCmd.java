package com.epam.task4.controller.command;

import com.epam.task4.AppContext;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.model.data_sources.ProductCatalog;
import com.epam.task4.service.ProductService;
import com.epam.task6.util.UtilProductCatalog;

/**
 * @author Oleksii Kushch
 */
public class CloseShopCmd implements Command {
    public static final String FULL_KEY = "--close";

    private static final String DESCRIPTION = "Close the shop";

    private final ProductService productService;

    public CloseShopCmd(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        UtilProductCatalog.safeProductCatalog(AppContext.PATH_PRODUCT_CATALOG, new ProductCatalog(productService.getAllProducts()));
        MainApp.stop();
        MainApp.printMessage(ShopLiterals.MSG_WHEN_APP_STOP);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
