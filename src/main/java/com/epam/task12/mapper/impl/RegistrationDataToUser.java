package com.epam.task12.mapper.impl;

import com.epam.task11.entity.User;
import com.epam.task11.util.RegistrationData;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;

/**
 * @author Oleksii Kushch
 */
public class RegistrationDataToUser implements Mapper<RegistrationData, User> {
    @Override
    public void map(RegistrationData registrationData, User user) throws MapException {
        user.setEmail(registrationData.getEmail());
        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setPassword(registrationData.getPassword());
    }
}
