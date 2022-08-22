package com.epam.task12.util;

/**
 * @author Oleksii Kushch
 */
public class LoginData {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /** login non-sensitive data (without password) */
    public String toStringWithoutSensitiveData() {
        return "LoginData{" +
                "email='" + email + '\'' +
                '}';
    }
}
