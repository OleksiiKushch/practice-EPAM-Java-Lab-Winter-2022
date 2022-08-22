package com.epam.task12.db.dao.impl.mysql;

/**
 * @author Oleksii Kushch
 */
public abstract class MySqlConstant {
    public static class UserField {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String PASSWORD = "password";
    }

    public static class UserQuery {
        public static final String GET_USER_BY_EMAIL = "SELECT * FROM `user` WHERE email = ?";
        public static final String CREATE_USER = "INSERT INTO `user` (id, email, first_name, last_name, `password`) VALUES (DEFAULT, ?, ?, ?, ?)";
    }
}
