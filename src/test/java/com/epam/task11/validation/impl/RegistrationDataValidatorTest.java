package com.epam.task11.validation.impl;

import com.epam.task11.util.RegistrationData;
import com.epam.task11.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationDataValidatorTest {
    Validator<RegistrationData> validator;
    RegistrationData testRegistrationData;

    @BeforeEach
    void setUp() {
        validator = new RegistrationDataValidator("9999", 20);
        testRegistrationData = new RegistrationData();
        testRegistrationData.setEmail("unicronix2002@gmail.com");
        testRegistrationData.setFirstName("Alex");
        testRegistrationData.setLastName("Fisher");
        testRegistrationData.setPassword("123456");
        testRegistrationData.setConfirmationPassword("123456");
        testRegistrationData.setCaptchaCode("9999");
        testRegistrationData.setCaptchaLifetime(10);
    }

    @Test
    void testIsValid_ifValid() {
        assertEquals("[]", validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifEmailIsInvalid() {
        testRegistrationData.setEmail("unicronix2002gmail.com");
        assertEquals("[Invalid format email address, must contain '@' symbol!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifEmailIsEmpty() {
        testRegistrationData.setEmail("");
        assertEquals("[Email address must be filled out (required field)!]",
                validator.isValid(testRegistrationData).toString());

        testRegistrationData.setEmail(null);
        assertEquals("[Email address must be filled out (required field)!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifFirstNameLengthTooLong() {
        testRegistrationData.setFirstName("Alexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        assertEquals("[The maximum allowed number of characters for first and last name is 40!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifFirstNameIsEmpty() {
        testRegistrationData.setFirstName("");
        assertEquals("[First and last name must be filled out (required fields)!]",
                validator.isValid(testRegistrationData).toString());

        testRegistrationData.setFirstName(null);
        assertEquals("[First and last name must be filled out (required fields)!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifLastNameLengthTooLong() {
        testRegistrationData.setLastName("Fisherrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        assertEquals("[The maximum allowed number of characters for first and last name is 40!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifLastNameIsEmpty() {
        testRegistrationData.setLastName("");
        assertEquals("[First and last name must be filled out (required fields)!]",
                validator.isValid(testRegistrationData).toString());

        testRegistrationData.setLastName(null);
        assertEquals("[First and last name must be filled out (required fields)!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifPasswordAndConfirmationPasswordNotMatch() {
        testRegistrationData.setPassword("123455");
        assertEquals("[Password and confirmation password must match!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifPasswordsTooLong() {
        testRegistrationData.setPassword("123456789123456789123456789");
        testRegistrationData.setConfirmationPassword("123456789123456789123456789");
        assertEquals("[Password cannot be shorter than 6 characters and longer than 18!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifPasswordsTooShort() {
        testRegistrationData.setPassword("123");
        testRegistrationData.setConfirmationPassword("123");
        assertEquals("[Password cannot be shorter than 6 characters and longer than 18!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifPasswordsAreEmpty() {
        testRegistrationData.setPassword("");
        testRegistrationData.setConfirmationPassword("");
        assertEquals("[Password and confirmation password must be filled out (required fields)!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifCaptchaCodeNotMatch() {
        testRegistrationData.setCaptchaCode("0000");
        assertEquals("[Captcha code didn't match, try again.]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifCaptchaCodeIsEmpty() {
        testRegistrationData.setCaptchaCode("");
        assertEquals("[Captcha code must be filled out (required fields and it's cannot be empty)!]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifCaptchaTimeout() {
        testRegistrationData.setCaptchaLifetime(30);
        assertEquals("[Captcha timeout (captcha is not valid), please try again a little faster :)]",
                validator.isValid(testRegistrationData).toString());
    }

    @Test
    void testIsValid_ifSeveralFieldsAreInvalid() {
        testRegistrationData.setEmail("unicronix2002gmail.com");
        testRegistrationData.setLastName("");
        assertEquals("[Invalid format email address, must contain '@' symbol!, " +
                        "First and last name must be filled out (required fields)!]",
                validator.isValid(testRegistrationData).toString());
    }
}