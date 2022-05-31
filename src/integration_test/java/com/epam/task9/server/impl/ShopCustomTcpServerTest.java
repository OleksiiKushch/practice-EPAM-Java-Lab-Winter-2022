package com.epam.task9.server.impl;

import com.epam.task4.mockdata.InitMockResources;
import com.epam.task4.model.data_sources.ProductCatalog;
import com.epam.task4.repository.impl.mock.ProductRepositoryImpl;
import com.epam.task4.service.ProductService;
import com.epam.task4.service.impl.ProductServiceImpl;
import com.epam.task9.ImitatorServerClient;
import com.epam.task9.server.Server;
import com.epam.task9.server.controller.ServerCommand;
import com.epam.task9.server.controller.ServerCommandContainer;
import com.epam.task9.server.controller.server_cmd.custom_tcp_server_impl.GetCountCustomTcpServerCmd;
import com.epam.task9.server.controller.server_cmd.custom_tcp_server_impl.GetItemByIdCustomTcpServerCmd;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

class ShopCustomTcpServerTest {
    @Test
    public void customTcpServerTest() throws InterruptedException {
        ProductService productService = new ProductServiceImpl(
                new ProductRepositoryImpl(InitMockResources.initProductCatalog(new ProductCatalog())));

        ServerCommandContainer commandContainer = new ServerCommandContainer();
        ServerCommand spyGetCountCmd = Mockito.spy(new GetCountCustomTcpServerCmd(productService));
        commandContainer.put(GetCountCustomTcpServerCmd.CMD_NAME, spyGetCountCmd);
        ServerCommand spyGetItemById = Mockito.spy(new GetItemByIdCustomTcpServerCmd(productService));
        commandContainer.put(GetItemByIdCustomTcpServerCmd.CMD_NAME, spyGetItemById);

        Server server = new CustomTcpServer(commandContainer);
        server.start();

        List<Thread> testClientsList = new ArrayList<>();
        List<String> testRequests = Arrays.asList("123", "get count", "get item=2", "get item=9", "get item",
                "get item=", "get item=7a", "get item=9=");
        for (String request : testRequests) {
            testClientsList.add(new ImitatorServerClient.Client(request, CustomTcpServer.PORT));
        }

        for (Thread client : testClientsList) {
            client.start();
            client.join();
        }

        assertEquals(6, spyGetCountCmd.getResult());
        Mockito.verify(spyGetCountCmd, times(2)).getResult();
        Mockito.verify(spyGetItemById, times(6)).getResult();

        server.stopServer();
    }
}