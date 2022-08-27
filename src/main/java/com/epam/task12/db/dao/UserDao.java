package com.epam.task12.db.dao;

import com.epam.task11.entity.User;
import com.epam.task13.db.dao.DaoException;

/**
 * @author Oleksii Kushch
 */
public interface UserDao {
    int insert(User user) throws DaoException;
    User getUserForEmail(String email) throws DaoException;
}
