package com.epam.task16.service;

import com.epam.task16.entity.user.UserRole;

public interface UserRoleService {
    UserRole getUserRoleById(int id);
    UserRole getUserRoleByName(String name);
}
