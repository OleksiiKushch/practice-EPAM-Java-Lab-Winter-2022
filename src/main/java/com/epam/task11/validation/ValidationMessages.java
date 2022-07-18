package com.epam.task11.validation;

public final class ValidationMessages {
    public static final String EMAIL_REQUIRED_FIELD = "Email address must be filled out (required field)!";
    public static final String INVALID_EMAIL = "Invalid format email address, must contain '@' symbol!";
    public static final String NAMES_REQUIRED_FIELDS = "First and last name must be filled out (required fields)!";
    public static final String NAMES_MAXIMUM_LENGTH = "The maximum allowed number of characters for first and last name is 40!";
    public static final String PASSWORDS_MUST_MATCH = "Password and confirmation password must match!";
    public static final String PASSWORDS_REQUIRED_FIELDS = "Password and confirmation password must be filled out (required fields)!";
    public static final String PASSWORDS_LENGTH_RESTRICTIONS = "Password cannot be shorter than 6 characters and longer than 18!";
    public static final String CAPTCHA_CODE_REQUIRED_FIELDS = "Captcha code must be filled out (required fields and it's cannot be empty)!";
    public static final String CAPTCHA_TIMEOUT = "Captcha timeout (captcha is not valid), please try again a little faster :)";
    public static final String CAPTCHA_CODE_NOT_MATCH = "Captcha code didn't match, try again.";
}
