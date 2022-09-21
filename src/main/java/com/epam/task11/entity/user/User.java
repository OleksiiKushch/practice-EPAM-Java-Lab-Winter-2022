package com.epam.task11.entity.user;

import com.epam.task11.entity.Entity;
import com.epam.task16.entity.user.UserRole;

/**
 * @author Oleksii Kushch
 */
public class User extends Entity {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole role;

    public User() {
    }

    public User(String email, String firstName, String lastName, String password, UserRole role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    /** user non-sensitive data (without password) */
    public String toStringWithoutSensitiveData() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private final User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder withId(int id) {
            newUser.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder withFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        public Builder withPassword(String password) {
            newUser.password = password;
            return this;
        }

        public Builder withRole(UserRole role) {
            newUser.role = role;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
