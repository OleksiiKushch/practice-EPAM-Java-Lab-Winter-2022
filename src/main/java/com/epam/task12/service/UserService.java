package com.epam.task12.service;

import com.epam.task11.entity.user.User;
import com.epam.task11.service.ServiceException;
import com.epam.task11.util.RegistrationData;
import com.epam.task12.service.transaction.Transaction;
import com.epam.task12.util.LoginData;

/**
 * @author Oleksii Kushch
 */
public interface UserService {
    @Transaction
    User registration(RegistrationData registrationData) throws ServiceException;
    @Transaction
    User login(LoginData loginData) throws ServiceException;
    User getUserByEmail(String email);
}
