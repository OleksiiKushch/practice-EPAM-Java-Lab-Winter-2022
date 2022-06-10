package com.epam.task9.server.impl;

import com.epam.task1.entity.Commodity;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;

class CustomTcpServerTest {

    @Test
    public void testSimpleRequests() throws InterruptedException {
        List<Commodity> spyProductList = Mockito.spy(new ArrayList<>());
        Mockito.doReturn(32).when(spyProductList).size();

        ProductService productService = Mockito.mock(ProductServiceImpl.class);
        Mockito.when(productService.getAllProducts()).thenReturn(spyProductList);

        Commodity mockItem = Mockito.mock(Commodity.class);
        Mockito.when(mockItem.getFrontTitle()).thenReturn("Book:\"Dune\"");
        Mockito.when(mockItem.getPrice()).thenReturn(new BigDecimal("12.4"));
        Mockito.when(productService.getProductById(2L)).thenReturn(mockItem);

        ServerCommand spyGetCountCmd = Mockito.spy(new GetCountCustomTcpServerCmd(productService));
        ServerCommand spyGetItemById = Mockito.spy(new GetItemByIdCustomTcpServerCmd(productService));

        ServerCommandContainer commandContainer = Mockito.mock(ServerCommandContainer.class);
        Mockito.when(commandContainer.isContainCommand(or(eq("get count"), eq("get item"))))
                .thenReturn(true);
        Mockito.when(commandContainer.getCommandByKey("get count"))
                .thenReturn(spyGetCountCmd);
        Mockito.when(commandContainer.getCommandByKey("get item"))
                .thenReturn(spyGetItemById);

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

        assertEquals(32, spyGetCountCmd.getResult());
        Mockito.verify(spyGetCountCmd, times(2)).getResult();
        Mockito.verify(spyGetItemById, times(6)).getResult();

        server.stopServer();
    }
}