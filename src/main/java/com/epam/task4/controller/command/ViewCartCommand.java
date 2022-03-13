package com.epam.task4.controller.command;

import com.epam.task4.util.Cart;
import com.epam.task4.mockdata.MockProductCatalog;
import com.epam.task4.controller.Command;

import java.util.stream.Collectors;

/**
 * @author Oleksii Kushch
 */
public class ViewCartCommand implements Command {
    @Override
    public void execute() {
        Cart cart = Cart.getInstance();
        cart.getContainer().forEach((id, amount) ->
                System.out.println(amount + "x - '" + MockProductCatalog.getInstance()
                    .getProductList().stream().filter(commodity -> commodity.getId().equals(id))
                        .collect(Collectors.toList()).get(0).getFrontTitle() + "'"));
        System.out.println("Sum: " + cart.getSum());
    }

    @Override
    public String getDescription() {
        return "View the contents of the cart with its total sum";
    }
}
