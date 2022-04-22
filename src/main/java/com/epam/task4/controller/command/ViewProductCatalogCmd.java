package com.epam.task4.controller.command;

import com.epam.task1.entity.Commodity;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.service.ProductService;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ViewProductCatalogCmd implements Command {
    public static final String FULL_KEY = "--product-catalog";
    public static final String SHORT_KEY = "-pc";

    private static final String DESCRIPTION = "Display a list of products (product catalog)";

    private final ProductService productService;

    public ViewProductCatalogCmd(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        List<Commodity> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println(ShopLiterals.MSG_PRODUCT_CATALOG_IS_EMPTY);
        } else {
            products.forEach(product -> System.out.println(product.toStringOptional()));
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
