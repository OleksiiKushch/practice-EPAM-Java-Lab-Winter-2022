package com.epam.task12.validation.impl;

import com.epam.task11.validation.Validator;
import com.epam.task12.util.LoginData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginDataValidatorTest {
    Validator<LoginData> validator;
    LoginData testLoginData;

    @BeforeEach
    void setUp() {
        validator = new LoginDataValidator();
        testLoginData = new LoginData();
        testLoginData.setEmail("unicronix2002@gmail.com");
        testLoginData.setPassword("123456");
    }

    @Test
    void testValid_ifValid() {
        assertEquals("[]", validator.isValid(testLoginData).toString());
    }

    @Test
    void testIsValid_ifEmailIsInvalid() {
        testLoginData.setEmail("unicronix2002gmail.com");
        assertEquals("[Invalid format email address, must contain '@' symbol!]",
                validator.isValid(testLoginData).toString());
    }

    @Test
    void testIsValid_ifEmailIsEmpty() {
        testLoginData.setEmail("");
        assertEquals("[Email address must be filled out (required field)!]",
                validator.isValid(testLoginData).toString());

        testLoginData.setEmail(null);
        assertEquals("[Email address must be filled out (required field)!]",
                validator.isValid(testLoginData).toString());
    }

    @Test
    void testIsValid_ifPasswordTooLong() {
        testLoginData.setPassword("123456789123456789123456789");
        assertEquals("[Password cannot be shorter than 6 characters and longer than 18!]",
                validator.isValid(testLoginData).toString());
    }

    @Test
    void testIsValid_ifPasswordTooShort() {
        testLoginData.setPassword("123");
        assertEquals("[Password cannot be shorter than 6 characters and longer than 18!]",
                validator.isValid(testLoginData).toString());
    }

    @Test
    void testIsValid_ifPasswordAreEmpty() {
        testLoginData.setPassword("");
        assertEquals("[Password must be filled out (required field)!]",
                validator.isValid(testLoginData).toString());
    }
}