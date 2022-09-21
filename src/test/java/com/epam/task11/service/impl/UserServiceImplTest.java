package com.epam.task11.service.impl;

import com.epam.task11.entity.User;
import com.epam.task11.repository.UserRepository;
import com.epam.task11.repository.impl.mock.UserRepositoryMockImpl;
import com.epam.task11.service.ServiceException;
import com.epam.task11.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryMockImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testCreate_ifValid() throws ServiceException {
        userService.create(new User.Builder().withEmail("unicronix2002@gmail.com").build());
        assertTrue(userRepository.isContainUser("unicronix2002@gmail.com"));
    }

    @Test
    void testCreate_ifUserWithThisEmailAlreadyExists() {
        assertThrows(ServiceException.class, () ->
                userService.create(new User.Builder().withEmail("UnicroniX2001@gmail.com").build()));
    }
}