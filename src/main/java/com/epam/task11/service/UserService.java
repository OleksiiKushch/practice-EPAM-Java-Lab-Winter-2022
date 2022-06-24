package com.epam.task11.service;

import com.epam.task11.entity.User;

public interface UserService {
    void create(User user) throws MyServiceException;
}
