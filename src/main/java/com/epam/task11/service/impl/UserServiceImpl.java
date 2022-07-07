package com.epam.task11.service.impl;

import com.epam.task11.entity.User;
import com.epam.task11.repository.UserRepository;
import com.epam.task11.service.MyServiceException;
import com.epam.task11.service.ServiceMessages;
import com.epam.task11.service.UserService;

import java.util.Locale;

/**
 * @author Oleksii Kushch
 */
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) throws MyServiceException {
        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));
        if (userRepository.isContainUser(user.getEmail())) {
            throw new MyServiceException(ServiceMessages.ACCOUNT_ALREADY_EXISTS);
        }
        userRepository.insert(user);
    }
}
