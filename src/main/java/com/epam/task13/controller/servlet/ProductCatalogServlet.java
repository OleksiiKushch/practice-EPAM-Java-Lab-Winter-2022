package com.epam.task13.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.connection.impl.PoolConnectionBuilder;
import com.epam.task13.db.dao.ProductDao;
import com.epam.task13.db.dao.impl.mysql.MySqlProductCategoryDao;
import com.epam.task13.db.dao.impl.mysql.MySqlProductDao;
import com.epam.task13.db.dao.impl.mysql.MySqlProductManufacturerDao;
import com.epam.task13.entity.Product;
import com.epam.task13.entity.ProductCategory;
import com.epam.task13.entity.ProductManufacturer;
import com.epam.task13.mapper.impl.HttpServletRequestToPagePaginationData;
import com.epam.task13.mapper.impl.HttpServletRequestToProductFilterFormBean;
import com.epam.task13.mapper.impl.HttpServletRequestToSortingData;
import com.epam.task13.service.ProductCategoryService;
import com.epam.task13.service.ProductManufacturerService;
import com.epam.task13.service.ProductService;
import com.epam.task13.service.impl.ProductCategoryServiceImpl;
import com.epam.task13.service.impl.ProductManufacturerServiceImpl;
import com.epam.task13.service.impl.ProductServiceImpl;
import com.epam.task13.util.PagePaginationData;
import com.epam.task13.util.ProductFilterFormBean;
import com.epam.task13.util.SortingData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/productCatalog")
public class ProductCatalogServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ProductCatalogServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = PoolConnectionBuilder.getInstance();

        ProductManufacturerService productManufacturerService = new ProductManufacturerServiceImpl(new MySqlProductManufacturerDao(connectionBuilder), new MySqlProductDao(connectionBuilder));
        List<ProductManufacturer> manufacturers = productManufacturerService.getAll();
        LOG.debug("All manufacturers: " + manufacturers);

        ProductCategoryService productCategoryService = new ProductCategoryServiceImpl(new MySqlProductCategoryDao(connectionBuilder), new MySqlProductDao(connectionBuilder));
        List<ProductCategory> categories = productCategoryService.getAll();
        LOG.debug("All categories: " + categories);

        PagePaginationData pagePaginationData = new PagePaginationData();
        new HttpServletRequestToPagePaginationData().map(request, pagePaginationData);
        LOG.debug("Page pagination data: " + pagePaginationData);

        ProductDao productDao = new MySqlProductDao(connectionBuilder);
        ProductService productService = new ProductServiceImpl(productDao,
                new ProductManufacturerServiceImpl(new MySqlProductManufacturerDao(connectionBuilder), productDao),
                new ProductCategoryServiceImpl(new MySqlProductCategoryDao(connectionBuilder), productDao));

        ProductFilterFormBean productFilterFormBean = new ProductFilterFormBean();
        new HttpServletRequestToProductFilterFormBean(manufacturers, categories).map(request, productFilterFormBean);

        SortingData sortingData = new SortingData();
        new HttpServletRequestToSortingData().map(request, sortingData);
        LOG.debug("Product sorting data: " + sortingData);

        List<Product> products = productService.findProducts(productFilterFormBean, sortingData, pagePaginationData);
        LOG.debug("Found products: " + products);
        int numberOfProductsWithFiltering = productService.getCountOfProductsWithFiltering(productFilterFormBean);
        LOG.debug("Total number of products with this filtering parameters: " + numberOfProductsWithFiltering);

        if (numberOfProductsWithFiltering > 0) { // update product price range respectively founded  products
            BigDecimal minProductPriceWithFiltering = productService.getMinProductPriceWithFiltering(productFilterFormBean);
            BigDecimal maxProductPriceWithFiltering = productService.getMaxProductPriceWithFiltering(productFilterFormBean);
            productFilterFormBean.setMinPrice(minProductPriceWithFiltering);
            productFilterFormBean.setMaxPrice(maxProductPriceWithFiltering);
        }

        LOG.debug("Product filter form bean: " + productFilterFormBean);

        pagePaginationData.processAuxiliaryParameters(numberOfProductsWithFiltering);
        LOG.debug("Page pagination data: " + pagePaginationData);

        request.setAttribute(ShopLiterals.PRODUCT_MANUFACTURERS, manufacturers);
        request.setAttribute(ShopLiterals.PRODUCT_CATEGORIES, categories);
        request.setAttribute(ShopLiterals.PRODUCTS, products);
        request.setAttribute(ShopLiterals.PAGE_PAGINATION_DATA, pagePaginationData);

        request.setAttribute(ShopLiterals.PRODUCT_FILTER_DATA, productFilterFormBean);

        request.setAttribute(ShopLiterals.SORTING_DATA, sortingData);

        request.setAttribute(ShopLiterals.NUMBER_OF_PRODUCTS, numberOfProductsWithFiltering);

        request.getRequestDispatcher("/WEB-INF/view/jsp/product_catalog.jsp").forward(request, response);
    }
}
