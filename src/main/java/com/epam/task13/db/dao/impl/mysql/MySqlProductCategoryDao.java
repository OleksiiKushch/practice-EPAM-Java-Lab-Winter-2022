package com.epam.task13.db.dao.impl.mysql;

import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task13.db.dao.ProductCategoryDao;
import com.epam.task13.entity.product.ProductCategory;
import com.epam.task13.mapper.impl.ResultSetToProductCategory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class MySqlProductCategoryDao extends MySqlEntityDao<ProductCategory> implements ProductCategoryDao {
    private static final Logger LOG = LogManager.getLogger(MySqlProductCategoryDao.class);

    private final ConnectionBuilder connectionBuilder;

    public MySqlProductCategoryDao(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public List<ProductCategory> getAll() {
        return getAll(connectionBuilder, ProductCategory.class, new ResultSetToProductCategory(), MySqlConstant.ProductCategoryQuery.GET_ALL);
    }

    @Override
    public ProductCategory getForId(int id) {
        return getForId(id, connectionBuilder, ProductCategory.class, new ResultSetToProductCategory(), MySqlConstant.ProductCategoryQuery.GET_BY_ID);
    }
}
