package com.epam.task11.service;

import com.epam.task11.entity.user.User;

/**
 * @author Oleksii Kushch
 */
public interface UserService {
    void registration(User user) throws ServiceException;
}
