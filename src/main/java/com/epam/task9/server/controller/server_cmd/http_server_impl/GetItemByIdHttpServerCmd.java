package com.epam.task9.server.controller.server_cmd.http_server_impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.service.ProductService;
import com.epam.task9.server.controller.ServerCommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class GetItemByIdHttpServerCmd implements ServerCommand {
    private static final Logger log = LogManager.getLogger(GetItemByIdHttpServerCmd.class);

    public static final String CMD_NAME = "GET /shop/item";
    public static final int NUMBER_OF_ARGUMENTS = 1;
    public static final int INDEX_ID_ARGUMENT = 0;
    public static final String NAME = "name";
    public static final String PRICE = "price";
    private final ProductService productService;
    private Object result;

    public GetItemByIdHttpServerCmd(ProductService productService) {
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
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(NAME, product.getFrontTitle());
                jsonObject.put(PRICE, product.getPrice());
                result = jsonObject;
            }
        }
    }

    @Override
    public Object getResult() {
        return result;
    }
}
