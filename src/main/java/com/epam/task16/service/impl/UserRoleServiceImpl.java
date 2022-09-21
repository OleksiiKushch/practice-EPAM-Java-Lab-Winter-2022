package com.epam.task16.service.impl;

import com.epam.task16.db.UserRoleDao;
import com.epam.task16.entity.user.UserRole;
import com.epam.task16.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleDao userRoleDao;

    public UserRoleServiceImpl(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public UserRole getUserRoleById(int id) {
        return userRoleDao.getUserRoleForId(id);
    }

    @Override
    public UserRole getUserRoleByName(String name) {
        return userRoleDao.getUserRoleForName(name);
    }
}
