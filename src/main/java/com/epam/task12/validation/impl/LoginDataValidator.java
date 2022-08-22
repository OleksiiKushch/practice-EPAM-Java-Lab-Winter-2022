package com.epam.task12.validation.impl;

import com.epam.task11.validation.RegexPattern;
import com.epam.task11.validation.ValidationMessages;
import com.epam.task11.validation.Validator;
import com.epam.task12.util.LoginData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class LoginDataValidator implements Validator<LoginData> {
    @Override
    public List<String> isValid(LoginData loginData) {
        List<String> errors = new ArrayList<>();
        if (isNullOrBlank(loginData.getEmail())) {
            errors.add(ValidationMessages.EMAIL_REQUIRED_FIELD);
        } else if (!RegexPattern.EMAIL_PATTERN.matcher(loginData.getEmail().strip()).matches()) {
            errors.add(ValidationMessages.INVALID_EMAIL);
        }

        if (isNullOrBlank(loginData.getPassword())) {
            errors.add(ValidationMessages.PASSWORD_REQUIRED_FIELD);
        } else if (!RegexPattern.PASSWORD_PATTERN.matcher(loginData.getPassword().strip()).matches()) {
            errors.add(ValidationMessages.PASSWORDS_LENGTH_RESTRICTIONS);
        }

        return errors;
    }
}
