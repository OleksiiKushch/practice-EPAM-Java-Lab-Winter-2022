package com.epam.task9.server.controller.server_cmd.custom_tcp_server_impl;

import com.epam.task4.service.ProductService;
import com.epam.task9.server.controller.ServerCommand;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class GetCountCustomTcpServerCmd implements ServerCommand {
    public static final String CMD_NAME = "get count";
    private final ProductService productService;
    private Object result;

    public GetCountCustomTcpServerCmd(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(List<String> arguments) {
        result = productService.getAllProducts().size();
    }

    @Override
    public Object getResult() {
        return result;
    }
}
