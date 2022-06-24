package com.epam.task11.service.impl;

import com.epam.task11.entity.User;
import com.epam.task11.repository.UserRepository;
import com.epam.task11.repository.impl.mock.UserRepositoryMockImpl;
import com.epam.task11.service.MyServiceException;
import com.epam.task11.service.UserService;

import java.util.Locale;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryMockImpl();
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) throws MyServiceException {
        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));
        if (userRepository.isContainUser(user.getEmail())) {
            throw new MyServiceException("Account with this e-mail already exists in the system.");
        }
        userRepository.insert(user);
    }
}
