package com.epam.task4.controller;

import com.epam.task4.controller.command.PutProductToCartCommand;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CommandHolderTest {
    private final CommandHolder commandHolder = CommandHolder.getInstance();

    @Test
    void isContain() {
        assertTrue(commandHolder.isContain("--put-to-cart"));
        assertTrue(commandHolder.isContain("-ptc"));
        assertFalse(commandHolder.isContain("-b"));
    }

    @Test
    void getCommandByKey() {
        assertEquals(PutProductToCartCommand.class, commandHolder.getCommandByKey("-ptc").getClass());
        assertNull(commandHolder.getCommandByKey("-b"));
    }

    @Test
    void viewDescriptionAllCommands() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));


        commandHolder.viewDescriptionAllCommands();
        assertEquals(
                "'--cart' View the contents of the cart with its total sum\r\n" +
                "'--checkout' Buy all items from the cart (checkout)\r\n" +
                "'--latest-products' '-lp' View information about the last 5 product that were added to the cart in all shopping sessions\r\n" +
                "'--order-list' '-ol' Display a list of orders\r\n" +
                "'--order-list-from-to' '-olft' Display a list of orders for a certain period of time\r\n" +
                "'--order-nearest-date' '-ond' Displays the order by the nearest date\r\n" +
                "'--product-list' '-pl' Display a list of products\r\n" +
                "'--put-to-cart' '-ptc' Add product to cart",
                outputStreamCaptor.toString().trim());

        System.setOut(standardOut);
    }
}