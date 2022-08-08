package com.epam.task12.service;

import com.epam.task11.entity.User;
import com.epam.task11.service.MyServiceException;
import com.epam.task12.util.LoginData;

public interface UserService extends EntryService {
    boolean registration(User user) throws MyServiceException;
    User login(LoginData loginData) throws MyServiceException;
}
