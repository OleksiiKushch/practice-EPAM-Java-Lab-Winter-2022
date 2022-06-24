package com.epam.task11.util;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.entity.User;

import java.util.Arrays;
import java.util.List;

public class RegistrationData {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmationPassword;
    private String captchaCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    @Override
    public String toString() {
        return "RegistrationData{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", confirmationPassword='" + confirmationPassword + '\'' +
                ", captchaCode='" + captchaCode + '\'' +
                '}';
    }
    public User mapUser() {
        return new User.Builder()
                .withEmail(email)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPassword(password)
                .build();
    }

    public static RegistrationData mapRegistrationData(List<String> parameters) {
        RegistrationData result = new RegistrationData();
        int i = -1;
        result.setEmail(parameters.get(++i));
        result.setFirstName(parameters.get(++i));
        result.setLastName(parameters.get(++i));
        result.setPassword(parameters.get(++i));
        result.setConfirmationPassword(parameters.get(++i));
        result.setCaptchaCode(parameters.get(++i));
        return result;
    }
    public static List<String> getListStrParameters() {
        return Arrays.asList(ShopLiterals.EMAIL, ShopLiterals.FIRST_NAME, ShopLiterals.LAST_NAME,
                ShopLiterals.PASSWORD, ShopLiterals.CONFIRMATION_PASSWORD, ShopLiterals.CAPTCHA);
    }
}
