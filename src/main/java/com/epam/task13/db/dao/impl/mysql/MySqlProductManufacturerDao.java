package com.epam.task13.db.dao.impl.mysql;

import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task13.db.dao.ProductManufacturerDao;
import com.epam.task13.entity.product.ProductManufacturer;
import com.epam.task13.mapper.impl.ResultSetToProductManufacturer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class MySqlProductManufacturerDao extends MySqlEntityDao<ProductManufacturer> implements ProductManufacturerDao {
    private static final Logger LOG = LogManager.getLogger(MySqlProductManufacturerDao.class);

    private final ConnectionBuilder connectionBuilder;

    public MySqlProductManufacturerDao(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public List<ProductManufacturer> getAll() {
        return getAll(connectionBuilder, ProductManufacturer.class, new ResultSetToProductManufacturer(), MySqlConstant.ProductManufacturerQuery.GET_ALL);
    }

    @Override
    public ProductManufacturer getForId(int id) {
        return getForId(id, connectionBuilder, ProductManufacturer.class, new ResultSetToProductManufacturer(), MySqlConstant.ProductManufacturerQuery.GET_BY_ID);
    }
}
