package com.epam.task11.validation.impl;

import com.epam.task11.util.RegistrationData;
import com.epam.task11.validation.RegexPattern;
import com.epam.task11.validation.ValidationMessages;
import com.epam.task11.validation.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class RegistrationDataValidator implements Validator<RegistrationData> {
    private String captchaCode;
    private int captchaTimeout;

    public RegistrationDataValidator() {
    }

    public RegistrationDataValidator(String captchaCode, int captchaTimeout) {
        this.captchaCode = captchaCode;
        this.captchaTimeout = captchaTimeout;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public int getCaptchaTimeout() {
        return captchaTimeout;
    }

    public void setCaptchaTimeout(int captchaTimeout) {
        this.captchaTimeout = captchaTimeout;
    }

    @Override
    public List<String> isValid(RegistrationData registrationData) {
        List<String> errors = new ArrayList<>();
        if (isNullOrBlank(registrationData.getEmail())) {
            errors.add(ValidationMessages.EMAIL_REQUIRED_FIELD);
        } else if (!RegexPattern.EMAIL_PATTERN.matcher(registrationData.getEmail().strip()).matches()) {
            errors.add(ValidationMessages.INVALID_EMAIL);
        }

        if (isNullOrBlank(registrationData.getFirstName()) || isNullOrBlank(registrationData.getLastName())) {
            errors.add(ValidationMessages.NAMES_REQUIRED_FIELDS);
        } else if (!RegexPattern.SHORT_NAME_PATTERN.matcher(registrationData.getFirstName().strip()).matches()
                || !RegexPattern.SHORT_NAME_PATTERN.matcher(registrationData.getLastName().strip()).matches()) {
            errors.add(ValidationMessages.NAMES_MAXIMUM_LENGTH);
        }

        if (!registrationData.getPassword().equals(registrationData.getConfirmationPassword())) {
            errors.add(ValidationMessages.PASSWORDS_MUST_MATCH);
        } else if (isNullOrBlank(registrationData.getPassword()) || isNullOrBlank(registrationData.getConfirmationPassword())) {
            errors.add(ValidationMessages.PASSWORDS_REQUIRED_FIELDS);
        } else if (!RegexPattern.PASSWORD_PATTERN.matcher(registrationData.getPassword().strip()).matches()
                || !RegexPattern.PASSWORD_PATTERN.matcher(registrationData.getConfirmationPassword().strip()).matches()) {
            errors.add(ValidationMessages.PASSWORDS_LENGTH_RESTRICTIONS);
        }

        if (isNullOrBlank(registrationData.getCaptchaCode())) {
            errors.add(ValidationMessages.CAPTCHA_CODE_REQUIRED_FIELDS);
        } else if (registrationData.getCaptchaLifetime() > captchaTimeout) {
            errors.add(ValidationMessages.CAPTCHA_TIMEOUT);
        } else if (!registrationData.getCaptchaCode().strip().equals(captchaCode)) {
            errors.add(ValidationMessages.CAPTCHA_CODE_NOT_MATCH);
        }

        return errors;
    }
}
