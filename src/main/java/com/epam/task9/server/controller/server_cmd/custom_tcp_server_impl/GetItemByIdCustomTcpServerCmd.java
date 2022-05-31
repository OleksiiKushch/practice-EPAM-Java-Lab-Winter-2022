package com.epam.task9.server.controller.server_cmd.custom_tcp_server_impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.service.ProductService;
import com.epam.task9.server.controller.ServerCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.NumberFormat;
import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class GetItemByIdCustomTcpServerCmd implements ServerCommand {
    private static final Logger log = LogManager.getLogger(GetItemByIdCustomTcpServerCmd.class);

    public static final String CMD_NAME = "get item";
    public static final int NUMBER_OF_ARGUMENTS = 1;
    public static final int INDEX_ID_ARGUMENT = 0;
    private final ProductService productService;
    private Object result;

    public GetItemByIdCustomTcpServerCmd(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(List<String> arguments) {
        if (arguments == null || arguments.size() != NUMBER_OF_ARGUMENTS) {
            result = null;
        } else {
            Commodity product = null;
            try { // validate id from argument list
                product = productService.getProductById(Long.valueOf(arguments.get(INDEX_ID_ARGUMENT)));
            } catch (NumberFormatException exception) {
                log.debug(exception.getMessage());
            }
            if (product == null) {
                result = null;
            } else {
                result = StringUtils.join(product.getFrontTitle(), ShopLiterals.PIPELINE,
                        NumberFormat.getCurrencyInstance().format(product.getPrice()));
            }
        }
    }

    @Override
    public Object getResult() {
        return result;
    }
}
