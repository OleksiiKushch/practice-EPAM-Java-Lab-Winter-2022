package com.epam.task4.controller.command;

import com.epam.task4.util.Cart;
import com.epam.task4.controller.Command;

import java.util.Scanner;

public class PutProductToCartCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter id product which do you want add to cart:");
        Long id = scanner.nextLong();
        // TODO: validation id
        System.out.println("Please, enter amount of this product:");
        Integer amount = scanner.nextInt();
        // TODO: validation amount
        Cart.getInstance().put(id, amount);
        System.out.println("Product with id: " + id + " was successfully added to cart in amount: " + amount);
    }

    @Override
    public String getDescription() {
        return "Add product to cart";
    }
}
