package com.epam.task14.controller.servlet.change_cart;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task13.entity.Product;
import com.epam.task14.util.Cart;
import com.epam.task14.util.CartEntryFormData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/put_to_cart")
public class PutToCartServlet extends ChangeCartServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = getCart(request);
        Product product = getChangedProduct(request);
        CartEntryFormData cartEntryFormData = getCartEntry(request);
        cart.put(product, cartEntryFormData.getAmount());
        request.getSession().setAttribute(ShopLiterals.CART, cart);
    }
}
