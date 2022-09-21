USE bookshopdb;

INSERT INTO user_role (id, `name`)
VALUES
    (DEFAULT, 'customer'),
    (DEFAULT, 'admin');

SET @admin_user_role_id = (SELECT id FROM user_role WHERE `name` = 'admin');

INSERT INTO `user` (id, email, first_name,  last_name, `password`, role_id)
VALUES
	(DEFAULT, 'admin@i.ua', 'admin', 'admin', 'nimda0', @admin_user_role_id);

INSERT INTO product_category (id, `name`)
VALUES
    (DEFAULT, 'book'),
    (DEFAULT, 'audiobook'),
    (DEFAULT, 'ereader');

INSERT INTO product_manufacturer (id, `name`)
VALUES
    (DEFAULT, 'Colleen Hoover'),
    (DEFAULT, 'J.D. Robb'),
    (DEFAULT, 'GDr-512'),
    (DEFAULT, 'GDr-513a'),
    (DEFAULT, 'Chad Zunker'),
    (DEFAULT, 'Delia Owens');

SET @book_category_id = (SELECT id FROM product_category WHERE `name` = 'book');
SET @audiobook_category_id = (SELECT id FROM product_category WHERE `name` = 'audiobook');
SET @ereader_category_id = (SELECT id FROM product_category WHERE `name` = 'ereader');

SET @colleen_hoover_id = (SELECT id FROM product_manufacturer WHERE `name` = 'Colleen Hoover');
SET @j_d_robb_id = (SELECT id FROM product_manufacturer WHERE `name` = 'J.D. Robb');
SET @gdr_513_id = (SELECT id FROM product_manufacturer WHERE `name` = 'GDr-512');
SET @gdr_513a_id = (SELECT id FROM product_manufacturer WHERE `name` = 'GDr-513a');
SET @chad_zunker_id = (SELECT id FROM product_manufacturer WHERE `name` = 'Chad Zunker');
SET @delia_owens_id = (SELECT id FROM product_manufacturer WHERE `name` = 'Delia Owens');

INSERT INTO product (id, `name`, price,  manufacturer_id, category_id)
VALUES
	(DEFAULT, 'Verity (Colleen H.)', 10.99, @colleen_hoover_id, @book_category_id),
	(DEFAULT, 'Abandoned in Death (J.D. Robb)', 14.99, @j_d_robb_id, @book_category_id),
	(DEFAULT, 'Ugly Love (Colleen H.)', 9.68, @colleen_hoover_id, @book_category_id),
	(DEFAULT, 'E-Reader12 (GDr-512)', 28.98, @gdr_513_id, @ereader_category_id),
	(DEFAULT, 'E-Reader13a+ (GDr-513a)', 36.68, @gdr_513a_id, @ereader_category_id),
	(DEFAULT, 'Family Money (Chad Z.)', 4.99, @chad_zunker_id, @audiobook_category_id),
	(DEFAULT, 'Where the Crawdads Sing (Delia O.)', 18.99, @delia_owens_id, @book_category_id),
    (DEFAULT, 'Abandoned in Audio', 6.54, @j_d_robb_id, @audiobook_category_id),
    (DEFAULT, 'E-Reader11', 42.10, @gdr_513_id, @ereader_category_id),
    (DEFAULT, 'E-Reader9s', 27.49, @gdr_513_id, @ereader_category_id),
    (DEFAULT, 'E-Reader9sa', 29.98, @gdr_513a_id, @ereader_category_id),
    (DEFAULT, 'Verity Audio', 13.50, @colleen_hoover_id, @audiobook_category_id),
    (DEFAULT, 'Ugly Audio', 7.89, @colleen_hoover_id, @audiobook_category_id);

INSERT INTO order_status (id, `name`)
VALUES
    (1, 'taken'), -- принят
    (2, 'confirmed'), -- подтвержден
    (3, 'formed'), -- формируется
    (4, 'expelled'), -- выслан
    (5, 'completed'), -- завершен
    (6, 'canceled'); -- отменен
