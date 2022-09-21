package com.epam.task14.controller.servlet.change_cart;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task13.entity.Product;
import com.epam.task14.util.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_from_cart")
public class DeleteFromCartServlet extends ChangeCartServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = getCart(request);
        Product product = getChangedProduct(request);
        cart.remove(product);
        request.getSession().setAttribute(ShopLiterals.CART, cart);
        request.getRequestDispatcher("/WEB-INF/view/jsp/general/my_cart.jsp").forward(request, response);
    }
}
