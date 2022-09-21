package com.epam.task11.util;

import javax.servlet.http.Part;

/**
 * @author Oleksii Kushch
 */
public class RegistrationData {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmationPassword;
    private String captchaCode;
    private int captchaLifetime;
    private Part avatar;

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

    public int getCaptchaLifetime() {
        return captchaLifetime;
    }

    public void setCaptchaLifetime(int captchaLifetime) {
        this.captchaLifetime = captchaLifetime;
    }

    public Part getAvatar() {
        return avatar;
    }

    public void setAvatar(Part avatar) {
        this.avatar = avatar;
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
                ", captchaLifetime=" + captchaLifetime +
                ", avatar=" + avatar +
                '}';
    }

    /** registration non-sensitive data (without password and confirmation password) */
    public String toStringWithoutSensitiveData() {
        return "RegistrationData{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", captchaCode='" + captchaCode + '\'' +
                ", captchaLifetime=" + captchaLifetime +
                ", avatar=" + avatar +
                '}';
    }
}
