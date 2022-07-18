package com.epam.task11.validation;

import java.util.regex.Pattern;

/**
 * @author Oleksii Kushch
 */
public final class RegexPattern {
    public static final int MIN_LENGTH_NAME = 1;
    public static final int MAX_LENGTH_SHORT_NAME = 40;
    public static final int MIN_LENGTH_PASSWORD = 6;
    public static final int MAX_LENGTH_PASSWORD = 18;

    public static final String EMAIL_REGEX = "(.+)@(.+)";
    public static final String SHORT_NAME_REGEX = "[\\s\\S]{" + MIN_LENGTH_NAME + "," + MAX_LENGTH_SHORT_NAME + "}";
    public static final String PASSWORD_REGEX = "[\\S]{" + MIN_LENGTH_PASSWORD + "," + MAX_LENGTH_PASSWORD + "}";

    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public static final Pattern SHORT_NAME_PATTERN = Pattern.compile(SHORT_NAME_REGEX);
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
}
