package com.epam.task12.service.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.entity.user.User;
import com.epam.task11.service.ServiceException;
import com.epam.task11.service.ServiceMessages;
import com.epam.task11.util.RegistrationData;
import com.epam.task12.db.dao.UserDao;
import com.epam.task12.mapper.impl.RegistrationDataToUser;
import com.epam.task12.service.UserService;
import com.epam.task12.service.transaction.Transaction;
import com.epam.task12.service.transaction.TransactionException;
import com.epam.task12.service.transaction.TransactionManager;
import com.epam.task12.util.LoginData;
import com.epam.task13.db.dao.DaoException;
import com.epam.task13.service.impl.MediaServiceImpl;
import com.epam.task16.service.UserRoleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Proxy;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Oleksii Kushch
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    private static final String DEFAULT_USER_ROLE = ShopLiterals.CUSTOMER_USER_ROLE;

    private final UserDao userDao;
    private final TransactionManager transactionManager;
    private final UserRoleService userRoleService;

    private UserServiceImpl(UserDao userDao, TransactionManager transactionManager, UserRoleService userRoleService) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
        this.userRoleService = userRoleService;
    }

    /**
     * @return instance of UserServiceImpl with dynamic proxy that check:
     * if method annotated {@link Transaction} in {@link UserService} do it like transaction else do as is
     */
    public static UserService getInstance(UserDao userDao, TransactionManager transactionManager, UserRoleService userRoleService) {
        UserServiceImpl userService = new UserServiceImpl(userDao, transactionManager, userRoleService);
        return (UserService) Proxy.newProxyInstance(
                UserServiceImpl.class.getClassLoader(),
                new Class[] { UserService.class },
                (proxy, method, methodArgs) -> {
                    if (method.isAnnotationPresent(Transaction.class)) {
                        try {
                            LOG.debug("Method: " + method.getName() + " invoke transactionally");
                            return userService.transactionManager.doInTransaction(() -> method.invoke(userService, methodArgs));
                        } catch (TransactionException exception) {
                            throw new ServiceException(exception.getMessage());
                        }
                    } else {
                        LOG.debug("Method: " + method.getName() + " invoke NON transactionally");
                        return method.invoke(userService, methodArgs);
                    }
                });
    }

    @Override
    public User registration(RegistrationData registrationData) throws ServiceException {
        User user = new User();
        new RegistrationDataToUser().map(registrationData, user);
        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));
        user.setRole(userRoleService.getUserRoleByName(DEFAULT_USER_ROLE));
        try {
            User existingUser = userDao.getUserForEmail(user.getEmail());
            if (Objects.isNull(existingUser)) {
                userDao.insert(user);
            } else {
                throw new ServiceException(ServiceMessages.ACCOUNT_ALREADY_EXISTS);
            }
        } catch (DaoException exception) {
            LOG.warn(exception.getMessage());
            throw new ServiceException(exception.getMessage());
        }
        new MediaServiceImpl().saveImage(registrationData.getAvatar(), String.valueOf(user.getId()));
        return user;
    }

    @Override
    public User login(LoginData loginData) throws ServiceException {
        try {
            User user = getUserByEmail(loginData.getEmail());
            if (Objects.isNull(user) || !user.getPassword().equals(loginData.getPassword())) {
                throw new ServiceException(ServiceMessages.EMAIL_OR_PASSWORD_IS_INCORRECT);
            } else {
                user.setRole(userRoleService.getUserRoleById(user.getRole().getId()));
                return user;
            }
        } catch (DaoException exception) {
            LOG.warn(exception.getMessage());
            throw new ServiceException(exception.getMessage());
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserForEmail(email);
    }
}
