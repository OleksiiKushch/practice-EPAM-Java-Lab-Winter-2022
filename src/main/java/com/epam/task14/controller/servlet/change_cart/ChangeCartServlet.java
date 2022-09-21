package com.epam.task14.controller.servlet.change_cart;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.connection.impl.PoolConnectionBuilder;
import com.epam.task13.db.dao.ProductDao;
import com.epam.task13.db.dao.impl.mysql.MySqlProductCategoryDao;
import com.epam.task13.db.dao.impl.mysql.MySqlProductDao;
import com.epam.task13.db.dao.impl.mysql.MySqlProductManufacturerDao;
import com.epam.task13.entity.product.Product;
import com.epam.task13.service.ProductService;
import com.epam.task13.service.impl.ProductCategoryServiceImpl;
import com.epam.task13.service.impl.ProductManufacturerServiceImpl;
import com.epam.task13.service.impl.ProductServiceImpl;
import com.epam.task14.mapper.impl.HttpServletRequestToCartEntryFormData;
import com.epam.task14.util.Cart;
import com.epam.task14.util.CartEntryFormData;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public abstract class ChangeCartServlet extends HttpServlet {
    protected Product getChangedProduct(HttpServletRequest request) {
        ConnectionBuilder connectionBuilder = PoolConnectionBuilder.getInstance();
        ProductDao productDao = new MySqlProductDao(connectionBuilder);
        ProductService productService = new ProductServiceImpl(productDao,
                new ProductManufacturerServiceImpl(new MySqlProductManufacturerDao(connectionBuilder), productDao),
                new ProductCategoryServiceImpl(new MySqlProductCategoryDao(connectionBuilder), productDao));
        CartEntryFormData cartEntryFormData = getCartEntry(request);
        return productService.getProductById(cartEntryFormData.getProductId());
    }

    protected CartEntryFormData getCartEntry(HttpServletRequest request) {
        CartEntryFormData cartEntryFormData = new CartEntryFormData();
        new HttpServletRequestToCartEntryFormData().map(request, cartEntryFormData);
        return cartEntryFormData;
    }

    protected Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(ShopLiterals.CART);
        if (Objects.isNull(cart)) {
            cart = new Cart();
        }
         return cart;
    }
}
