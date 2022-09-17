package com.epam.task13.db.dao.impl.mysql;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task13.db.dao.DaoException;
import com.epam.task13.db.dao.ProductDao;
import com.epam.task13.entity.product.Product;
import com.epam.task13.mapper.impl.ProductFilterFormBeanToPreparedStatement;
import com.epam.task13.mapper.impl.ProductToPreparedStatement;
import com.epam.task13.mapper.impl.ResultSetToProduct;
import com.epam.task13.util.PagePaginationData;
import com.epam.task13.util.ProductFilterFormBean;
import com.epam.task13.util.SortingData;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MySqlProductDao extends MySqlEntityDao<Product> implements ProductDao {
    private static final Logger LOG = LogManager.getLogger(MySqlProductDao.class);

    private final ConnectionBuilder connectionBuilder;

    public MySqlProductDao(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public int insert(Product product) throws DaoException {
        return insert(product, connectionBuilder, new ProductToPreparedStatement(), MySqlConstant.ProductQuery.INSERT);
    }

    @Override
    public Product getProductForId(int id) throws DaoException {
        return getForId(id, connectionBuilder, Product.class, new ResultSetToProduct(), MySqlConstant.ProductQuery.GET_BY_ID);
    }

    @Override
    public int getCount() throws DaoException {
        return getCount(connectionBuilder, MySqlConstant.ProductQuery.GET_COUNT);
    }

    @Override
    public List<Product> getAll() throws DaoException {
        return getAll(connectionBuilder, Product.class, new ResultSetToProduct(), MySqlConstant.ProductQuery.GET_ALL);
    }

    @Override
    public List<Product> getProductsWithPaginationSortingFiltration(ProductFilterFormBean productFilterFormBean, SortingData sortingData, PagePaginationData pagePaginationData) throws DaoException {
        List<Product> result = new ArrayList<>();
        String query = parseFilteringQuery(productFilterFormBean) + " " + parseSortingPartOfQuery(sortingData) + " LIMIT ? OFFSET ?";
        LOG.debug("query: " + query);
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            new ProductFilterFormBeanToPreparedStatement().map(productFilterFormBean, ps);
            // number of products that need skip
            int offset = pagePaginationData.getPageSize() * (pagePaginationData.getPageNumber() - 1);
            int numberOfFilteringParameters = productFilterFormBean.getNumberOfParametersSet();
            LOG.debug("numberOfFilteringParameters: " + numberOfFilteringParameters);
            ps.setInt(++numberOfFilteringParameters, pagePaginationData.getPageSize()); // limit
            ps.setInt(++numberOfFilteringParameters, offset);
            ResultSetToProduct mapper = new ResultSetToProduct();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    mapper.map(rs, product);
                    result.add(product);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }

    @Override
    public int getCountOfProductsWithFiltering(ProductFilterFormBean productFilterFormBean) {
        int result = 0;
        String query = parseFilteringQuery(productFilterFormBean).replaceFirst("\\*", "COUNT(*)");
        LOG.debug("query: " + query);
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            new ProductFilterFormBeanToPreparedStatement().map(productFilterFormBean, ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }

    @Override
    public int getCountForCategoryId(int categoryId) throws DaoException {
        int result = 0;
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(MySqlConstant.ProductQuery.GET_COUNT_FOR_CATEGORY_ID)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }

    @Override
    public int getCountForManufacturerId(int manufacturerId) throws DaoException {
        int result = 0;
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(MySqlConstant.ProductQuery.GET_COUNT_FOR_MANUFACTURER_ID)) {
            ps.setInt(1, manufacturerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }

    @Override
    public BigDecimal getMinProductPriceWithFiltering(ProductFilterFormBean productFilterFormBean) throws DaoException {
        return getMinMaxProductPriceWithFiltering(parseFilteringQuery(productFilterFormBean).replaceFirst("\\*", "MIN(price)"), productFilterFormBean);
    }

    @Override
    public BigDecimal getMaxProductPriceWithFiltering(ProductFilterFormBean productFilterFormBean) throws DaoException {
        return getMinMaxProductPriceWithFiltering(parseFilteringQuery(productFilterFormBean).replaceFirst("\\*", "MAX(price)"), productFilterFormBean);
    }

    private String parseFilteringQuery(ProductFilterFormBean productFilterFormBean) {
        StringBuilder result = new StringBuilder();
        result.append("SELECT * FROM " + MySqlConstant.ProductModel.TYPE_NAME);
        if (productFilterFormBean.getNumberOfParametersSet() > 0) {
            result.append(" WHERE true");
        }
        if (Objects.nonNull(productFilterFormBean.getNamePattern())) {
            result.append(" AND `" + MySqlConstant.ProductModel.NAME + "` LIKE ?");
        }
        if (Objects.nonNull(productFilterFormBean.getMinPrice())) {
            result.append(" AND " + MySqlConstant.ProductModel.PRICE + " >= ?");
        }
        if (Objects.nonNull(productFilterFormBean.getMaxPrice())) {
            result.append(" AND " + MySqlConstant.ProductModel.PRICE + " <= ?");
        }
        if (!productFilterFormBean.getManufactureIds().isEmpty()) {
            result.append(" AND (");
            if (productFilterFormBean.getManufactureIds().size() > 1) {
                result.append((MySqlConstant.ProductModel.MANUFACTURER_ID + " = ? OR ").repeat(productFilterFormBean.getManufactureIds().size() - 1));
            }
            result.append(MySqlConstant.ProductModel.MANUFACTURER_ID + " = ?)");
        }
        if (!productFilterFormBean.getCategoryIds().isEmpty()) {
            result.append(" AND (");
            if (productFilterFormBean.getCategoryIds().size() > 1) {
                result.append((MySqlConstant.ProductModel.CATEGORY_ID + " = ? OR ").repeat(productFilterFormBean.getCategoryIds().size() - 1));
            }
            result.append(MySqlConstant.ProductModel.CATEGORY_ID + " = ?)");
        }
        return result.toString();
    }

    private String parseSortingPartOfQuery(SortingData sortingData) {
        StringBuilder result = new StringBuilder();
        if (StringUtils.isNotEmpty(sortingData.getParameter())) {
            result.append("ORDER BY ");
            if (ShopLiterals.NAME.equals(sortingData.getParameter())) {
                result.append(MySqlConstant.ProductModel.NAME);
            } else if (ShopLiterals.PRICE.equals(sortingData.getParameter())) {
                result.append(MySqlConstant.ProductModel.PRICE);
            }
            if (Objects.nonNull(sortingData.getOrdering())) {
                if (ShopLiterals.SORT_DESCENT.equals(sortingData.getOrdering())) {
                    result.append(" DESC");
                }
            }
        }
        return result.toString();
    }

    private BigDecimal getMinMaxProductPriceWithFiltering(String query, ProductFilterFormBean productFilterFormBean) {
        LOG.debug("query: " + query);
        BigDecimal result = null;
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            new ProductFilterFormBeanToPreparedStatement().map(productFilterFormBean, ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getBigDecimal(1);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }
}
