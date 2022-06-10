package com.epam.task9.server.controller.server_cmd.http_server_impl;

import com.epam.task4.service.ProductService;
import com.epam.task9.server.controller.ServerCommand;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class GetCountHttpServerCmd implements ServerCommand {
    public static final String CMD_NAME = "GET /shop/count";
    private final ProductService productService;
    private Object result;

    public GetCountHttpServerCmd(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(List<String> arguments) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", productService.getAllProducts().size());
        result = jsonObject;
    }

    @Override
    public Object getResult() {
        return result;
    }
}
