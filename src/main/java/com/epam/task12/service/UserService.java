package com.epam.task12.service;

import com.epam.task11.entity.User;
import com.epam.task11.service.MyServiceException;
import com.epam.task12.service.transaction.Transaction;
import com.epam.task12.util.LoginData;

/**
 * @author Oleksii Kushch
 */
public interface UserService {
    boolean registration(User user) throws MyServiceException;
    @Transaction
    User login(LoginData loginData) throws MyServiceException;
}
