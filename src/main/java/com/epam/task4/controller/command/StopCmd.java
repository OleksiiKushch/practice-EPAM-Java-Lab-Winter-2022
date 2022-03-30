package com.epam.task4.controller.command;

import com.epam.task4.AppContext;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.util.UtilProductCatalog;

/**
 * @author Oleksii Kushch
 */
public class StopCmd implements Command {
    public static final String FULL_KEY = "--stop";
    public static final String DESCRIPTION = "Stop the application";

    @Override
    public void execute() {
        UtilProductCatalog.safeProductCatalog(AppContext.PATH_PRODUCT_CATALOG, MainApp.getContext().getProductCatalog());
        MainApp.stop();
        System.out.println(ShopLiterals.MSG_WHEN_APP_STOP);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
