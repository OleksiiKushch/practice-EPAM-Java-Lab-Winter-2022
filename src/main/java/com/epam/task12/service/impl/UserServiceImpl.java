package com.epam.task12.service.impl;

import com.epam.task11.entity.User;
import com.epam.task11.service.MyServiceException;
import com.epam.task11.service.ServiceMessages;
import com.epam.task12.db.dao.UserDao;
import com.epam.task12.service.UserService;
import com.epam.task12.service.transaction.TransactionManager;
import com.epam.task12.util.LoginData;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final TransactionManager transactionManager;

    public UserServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean registration(User user) {
        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));
        try {
            return transactionManager.doInTransaction(() -> {
                User existingUser = userDao.getUserForEmail(user.getEmail());
                if (Objects.isNull(existingUser)) {
                    int numOfRecords = userDao.create(user);
                    return isAddEnyRecordsToDb(numOfRecords);
                } else {
                    throw new MyServiceException(ServiceMessages.ACCOUNT_ALREADY_EXISTS);
                }
            });
        } catch (SQLException exception) {
            throw new MyServiceException(exception.getMessage());
        }
    }

    @Override
    public User login(LoginData loginData) {
        try {
            return transactionManager.doInTransaction(() -> {
                User user = userDao.getUserForEmail(loginData.getEmail());
                if (Objects.isNull(user) || !user.getPassword().equals(loginData.getPassword())) {
                    throw new MyServiceException(ServiceMessages.EMAIL_OR_PASSWORD_IS_INCORRECT);
                } else {
                    return user;
                }
            });
        } catch (SQLException exception) {
            throw new MyServiceException(exception.getMessage());
        }
    }
}
