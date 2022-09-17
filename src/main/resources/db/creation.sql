DROP DATABASE IF EXISTS bookshopdb;

CREATE DATABASE IF NOT EXISTS bookshopdb;

USE bookshopdb;

DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product_manufacturer;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS order_has_product;

CREATE TABLE IF NOT EXISTS product_category (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(45) NOT NULL UNIQUE KEY);

CREATE TABLE IF NOT EXISTS product_manufacturer (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(45) NOT NULL UNIQUE KEY);

CREATE TABLE IF NOT EXISTS product (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(256) NOT NULL,
price DECIMAL(9,2) NOT NULL,
manufacturer_id INT NOT NULL,
category_id INT NOT NULL,
CHECK (price>=0),
PRIMARY KEY (id),
INDEX fk_product_product_manufacturer1_idx (manufacturer_id) ,
INDEX fk_product_product_category1_idx (category_id) ,
CONSTRAINT fk_product_product_manufacturer1
  FOREIGN KEY (manufacturer_id)
      REFERENCES product_manufacturer (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
CONSTRAINT fk_product_product_category1
  FOREIGN KEY (category_id)
      REFERENCES product_category (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS user_role (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(45) NOT NULL UNIQUE KEY);

CREATE TABLE IF NOT EXISTS `user` (
id INT NOT NULL AUTO_INCREMENT,
email VARCHAR(320) NOT NULL,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
`password` VARCHAR(256) NOT NULL,
role_id INT NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY email (email),
FULLTEXT INDEX IX_email_fulltext (email),
INDEX fk_user_user_role1_idx (role_id) ,
CONSTRAINT fk_user_user_role1
  FOREIGN KEY (role_id)
      REFERENCES user_role (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS order_status (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45) NOT NULL UNIQUE KEY);

CREATE TABLE IF NOT EXISTS `order` (
id INT NOT NULL AUTO_INCREMENT,
status_id INT NOT NULL,
state_detail VARCHAR(512) NOT NULL,
delivery VARCHAR(1024) NOT NULL,
date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
user_id INT NOT NULL,
PRIMARY KEY (id),
INDEX fk_order_order_status1_idx (status_id),
INDEX fk_order_user1_idx (user_id),
CONSTRAINT fk_order_order_status1
   FOREIGN KEY (status_id)
       REFERENCES order_status (id)
       ON DELETE RESTRICT
       ON UPDATE CASCADE,
CONSTRAINT fk_order_user1
   FOREIGN KEY (user_id)
       REFERENCES `user` (id)
       ON DELETE CASCADE
       ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS order_has_product (
order_id INT NOT NULL,
product_id INT NOT NULL,
price DECIMAL(9,2) NOT NULL,
amount INT NOT NULL,
CHECK (price>=0),
CHECK (amount>=0),
PRIMARY KEY (order_id, product_id),
INDEX fk_order_has_product_order_idx (order_id),
INDEX fk_order_has_product_product1_idx (product_id),
CONSTRAINT fk_order_has_product_order
   FOREIGN KEY (order_id)
       REFERENCES `order` (id)
       ON DELETE RESTRICT
       ON UPDATE CASCADE,
CONSTRAINT fk_order_has_product_product1
   FOREIGN KEY (product_id)
       REFERENCES product (id)
       ON DELETE RESTRICT
       ON UPDATE CASCADE);

ALTER TABLE product_category AUTO_INCREMENT = 1;
ALTER TABLE product_manufacturer AUTO_INCREMENT = 1;
ALTER TABLE product AUTO_INCREMENT = 1;
ALTER TABLE user_role AUTO_INCREMENT = 1;
ALTER TABLE `user` AUTO_INCREMENT = 1;
ALTER TABLE order_status AUTO_INCREMENT = 1;
ALTER TABLE `order` AUTO_INCREMENT = 1;

