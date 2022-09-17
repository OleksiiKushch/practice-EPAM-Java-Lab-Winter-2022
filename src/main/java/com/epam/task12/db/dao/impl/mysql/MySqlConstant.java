package com.epam.task12.db.dao.impl.mysql;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Oleksii Kushch
 */
public abstract class MySqlConstant {
    public static class EntityModel {
        public static final String ID = "id";
    }

    public static class UserModel {
        public static final String TYPE_NAME = "user";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String PASSWORD = "password";
        public static final String ROLE_ID = "role_id";
    }

    public static class UserRoleModel {
        public static final String TYPE_NAME = "user_role";
        public static final String NAME = "name";
    }

    public static class ProductManufacturerModel {
        public static final String TYPE_NAME = "product_manufacturer";
        public static final String NAME = "name";
    }

    public static class ProductCategoryModel {
        public static final String TYPE_NAME = "product_category";
        public static final String NAME = "name";
    }

    public static class ProductModel {
        public static final String TYPE_NAME = "product";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String MANUFACTURER_ID = "manufacturer_id";
        public static final String CATEGORY_ID = "category_id";
    }

    public static class OrderModel {
        public static final String TYPE_NAME = "order";
        public static final String STATUS_ID = "status_id";
        public static final String STATE_DETAIL = "state_detail";
        public static final String DELIVERY = "delivery";
        public static final String DATE_TIME = "date_time";
        public static final String USER_ID = "user_id";
    }

    public static class OrderProductRelationModel {
        public static final String TYPE_NAME = "order_has_product";
        public static final String ORDER_ID = "order_id";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRICE = "price";
        public static final String AMOUNT = "amount";
    }

    public static class UserQuery {
        public static final String GET_BY_EMAIL = StringUtils.join("SELECT * FROM `", UserModel.TYPE_NAME, "` WHERE ", UserModel.EMAIL, " = ?");
        public static final String INSERT = StringUtils.join("INSERT INTO ", UserModel.TYPE_NAME, " (", EntityModel.ID, ", ", UserModel.EMAIL, ", ", UserModel.FIRST_NAME, ", ", UserModel.LAST_NAME, ", `", UserModel.PASSWORD, "`, " + UserModel.ROLE_ID + ") VALUES (DEFAULT, ?, ?, ?, ?, ?)");
    }

    public static class UserRoleQuery {
        public static final String GET_BY_ID = StringUtils.join("SELECT * FROM ", UserRoleModel.TYPE_NAME, " WHERE ", EntityModel.ID, " = ?");
        public static final String GET_BY_NAME = StringUtils.join("SELECT * FROM ", UserRoleModel.TYPE_NAME, " WHERE ", UserRoleModel.NAME, " = ?");
    }

    public static class ProductManufacturerQuery {
        public static final String GET_ALL = StringUtils.join("SELECT * FROM ", ProductManufacturerModel.TYPE_NAME);
        public static final String GET_BY_ID = StringUtils.join("SELECT * FROM ", ProductManufacturerModel.TYPE_NAME, " WHERE ", EntityModel.ID, " = ?");
    }

    public static class ProductCategoryQuery {
        public static final String GET_ALL = StringUtils.join("SELECT * FROM ", ProductCategoryModel.TYPE_NAME);
        public static final String GET_BY_ID = StringUtils.join("SELECT * FROM ", ProductCategoryModel.TYPE_NAME, " WHERE ", EntityModel.ID, " = ?");
    }

    public static class ProductQuery {
        public static final String INSERT = StringUtils.join("INSERT INTO ", ProductModel.TYPE_NAME, " (", EntityModel.ID, ", `", ProductModel.NAME, "`, ", ProductModel.PRICE, ", ", ProductModel.MANUFACTURER_ID, ", ", ProductModel.CATEGORY_ID, ") VALUES (DEFAULT, ?, ?, ?, ?)");
        public static final String GET_BY_ID = StringUtils.join("SELECT * FROM ", ProductModel.TYPE_NAME, " WHERE ", EntityModel.ID, " = ?");
        public static final String GET_COUNT = StringUtils.join("SELECT COUNT(*) FROM ", ProductModel.TYPE_NAME);
        public static final String GET_ALL = StringUtils.join("SELECT * FROM ", ProductModel.TYPE_NAME);
        public static final String GET_COUNT_FOR_CATEGORY_ID = StringUtils.join("SELECT COUNT(*) FROM ", ProductModel.TYPE_NAME, " WHERE ", ProductModel.CATEGORY_ID, " = ?");
        public static final String GET_COUNT_FOR_MANUFACTURER_ID = StringUtils.join("SELECT COUNT(*) FROM ", ProductModel.TYPE_NAME, " WHERE ", ProductModel.MANUFACTURER_ID, " = ?");
    }

    public static class OrderQuery {
        public static final String INSERT = StringUtils.join("INSERT INTO `", OrderModel.TYPE_NAME, "` (", EntityModel.ID, ", `", OrderModel.STATUS_ID, "`, ", OrderModel.STATE_DETAIL, ", ", OrderModel.DELIVERY, ", ", OrderModel.DATE_TIME, ", ", OrderModel.USER_ID, ") VALUES (DEFAULT, ?, ?, ?, DEFAULT, ?)");
    }

    public static class OrderProductRelationQuery {
        public static final String INSERT_HAS_PRODUCT = StringUtils.join("INSERT INTO ", OrderProductRelationModel.TYPE_NAME, " (", OrderProductRelationModel.ORDER_ID, ", ", OrderProductRelationModel.PRODUCT_ID, ", ", OrderProductRelationModel.PRICE, ", ", OrderProductRelationModel.AMOUNT, ") VALUES (?, ?, ?, ?)");
    }
}
