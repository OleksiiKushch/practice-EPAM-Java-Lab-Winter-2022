package com.epam.task11.validation.impl;

import com.epam.task11.util.RegistrationData;
import com.epam.task11.validation.RegexPattern;
import com.epam.task11.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDataValidator implements Validator<RegistrationData> {
    private String captchaCode;

    public RegistrationDataValidator() {
    }

    public RegistrationDataValidator(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
    @Override
    public List<String> isValid(RegistrationData registrationData) {
        List<String> errors = new ArrayList<>();
        if (isNullOrBlank(registrationData.getEmail())) {
            errors.add("Email address must be filled out (required field)!");
        } else if (!RegexPattern.EMAIL_PATTERN.matcher(registrationData.getEmail().strip()).matches()) {
            errors.add("Invalid format email address, must contain '@' symbol!");
        }

        if (isNullOrBlank(registrationData.getFirstName()) || isNullOrBlank(registrationData.getLastName())) {
            errors.add("First and last name must be filled out (required fields)!");
        } else if (!RegexPattern.SHORT_NAME_PATTERN.matcher(registrationData.getFirstName().strip()).matches()
                || !RegexPattern.SHORT_NAME_PATTERN.matcher(registrationData.getLastName().strip()).matches()) {
            errors.add("The maximum allowed number of characters for first and last name is 40!");
        }

        if (isNullOrBlank(registrationData.getPassword()) || isNullOrBlank(registrationData.getConfirmationPassword())) {
            errors.add("Password and confirmation password must be filled out (required fields)!");
        } else if (!RegexPattern.PASSWORD_PATTERN.matcher(registrationData.getPassword().strip()).matches()
                || !RegexPattern.PASSWORD_PATTERN.matcher(registrationData.getConfirmationPassword().strip()).matches()) {
            errors.add("Password cannot be shorter than 6 characters and longer than 18!");
        } else if (!registrationData.getPassword().equals(registrationData.getConfirmationPassword())) {
            errors.add("Password and confirmation password must match!");
        }

        if (isNullOrBlank(registrationData.getCaptchaCode())) {
            errors.add("Captcha code must be filled out (required fields and it's cannot be empty)!");
        } else if (!registrationData.getCaptchaCode().strip().equals(captchaCode)) {
            errors.add("Captcha code didn't match, try again.");
        }

        return errors;
    }
}
