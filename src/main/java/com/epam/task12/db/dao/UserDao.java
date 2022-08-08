package com.epam.task12.db.dao;

import com.epam.task11.entity.User;

import java.sql.SQLException;

public interface UserDao {
    User getUserForEmail(String email) throws SQLException;
    int create(User user) throws SQLException;
}
