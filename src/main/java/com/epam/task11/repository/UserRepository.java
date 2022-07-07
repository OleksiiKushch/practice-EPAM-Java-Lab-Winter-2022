package com.epam.task11.repository;

import com.epam.task11.entity.User;

/**
 * @author Oleksii Kushch
 */
public interface UserRepository {
    void insert(User user);
    boolean isContainUser(String email);
}
