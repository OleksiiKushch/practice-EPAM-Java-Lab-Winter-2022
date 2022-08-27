package com.epam.task13.db.dao.impl.mysql;

import com.epam.task11.entity.Entity;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.db.dao.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MySqlEntityDao<T extends Entity> {
    private static final Logger LOG = LogManager.getLogger(MySqlEntityDao.class);

    protected int insert(T entity, ConnectionBuilder connectionBuilder, Mapper<T, PreparedStatement> mapper, String query) throws DaoException {
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            mapper.map(entity, ps);
            int result = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                }
            }
            return result;
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    protected int getCount(ConnectionBuilder connectionBuilder, String query) {
        int result = 0;
        try (Connection connection = connectionBuilder.getConnection();
             ResultSet rs = connection.createStatement().executeQuery(query)) {
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }

    protected T getForId(int id, ConnectionBuilder connectionBuilder, Class<T> entityDescriptor, Mapper<ResultSet, T> mapper, String query) {
        T result = createEntityByDescriptor(entityDescriptor);
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mapper.map(rs, result);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }

    protected List<T> getAll(ConnectionBuilder connectionBuilder, Class<T> entityDescriptor, Mapper<ResultSet, T> mapper, String query) {
        List<T> result = new ArrayList<>();
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    T entity = createEntityByDescriptor(entityDescriptor);
                    mapper.map(rs, entity);
                    result.add(entity);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }

    /**
     * Replaces special characters of search input data for correct LIKE operator search.
     *
     * @param param input data for searching
     * @return escaping input from special characters
     */
    public static String escapeForLike(String param) {
        String result = param.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
        return "%" + result + "%";
    }

    private T createEntityByDescriptor(Class<T> entityDescriptor) {
        try {
            return entityDescriptor.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            LOG.warn(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
