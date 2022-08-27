package com.epam.task13.mapper.impl;

import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.db.dao.impl.mysql.MySqlEntityDao;
import com.epam.task13.util.ProductFilterFormBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class ProductFilterFormBeanToPreparedStatement implements Mapper<ProductFilterFormBean, PreparedStatement> {
    private static final Logger LOG = LogManager.getLogger(ProductFilterFormBeanToPreparedStatement.class);

    @Override
    public void map(ProductFilterFormBean productFilterFormBean, PreparedStatement preparedStatement) throws MapException {
        int i = 0;
        try {
            if (Objects.nonNull(productFilterFormBean.getNamePattern())) {
                preparedStatement.setString(++i, MySqlEntityDao.escapeForLike(productFilterFormBean.getNamePattern()));
            }
            if (Objects.nonNull(productFilterFormBean.getMinPrice())) {
                preparedStatement.setBigDecimal(++i, productFilterFormBean.getMinPrice());
            }
            if (Objects.nonNull(productFilterFormBean.getMaxPrice())) {
                preparedStatement.setBigDecimal(++i, productFilterFormBean.getMaxPrice());
            }
            for (int manufactureId : productFilterFormBean.getManufactureIds()) {
                preparedStatement.setInt(++i, manufactureId);
            }
            for (int categoryId : productFilterFormBean.getCategoryIds()) {
                preparedStatement.setInt(++i, categoryId);
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
