package com.epam.task16.db;

import com.epam.task16.entity.user.UserRole;

public interface UserRoleDao {
    UserRole getUserRoleForId(int id);
    UserRole getUserRoleForName(String name);
}
