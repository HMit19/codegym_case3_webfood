CREATE DATABASE `web_food`;

USE `web_food`;

CREATE TABLE `users`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(100) NOT NULL,
    `email`    VARCHAR(100) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    `phone`    VARCHAR(20)  NOT NULL,
    `address`  VARCHAR(150) NOT NULL,
    `role`     BIT DEFAULT 0, -- mặc định khi tài khoản mới được khởi tạo sẽ có quyền user
    `activate` BIT DEFAULT 1, -- mặc định khi tạo ra tài khoản sẽ ở trạng thái đang hoạt động
    PRIMARY KEY (`id`)
);

CREATE TABLE `categories`
(
    `id_category`   INT          NOT NULL AUTO_INCREMENT,
    `name_category` VARCHAR(150) NOT NULL UNIQUE,
    `exist`         BIT DEFAULT 1, -- mặc định trạng thái của một doanh mục là tồn tại
    PRIMARY KEY (`id_category`)
);

CREATE TABLE `products`
(
    `id_product`    INT          NOT NULL AUTO_INCREMENT,
    `name_product`  VARCHAR(150) NOT NULL,
    `price_product` LONG         NOT NULL,
    `image_product` VARCHAR(350),
    `description`   LONGTEXT,
    `available`     BIT DEFAULT 1, -- mặc định các sản phẩm tạo mới đều có sẵn
    `id_category`   INT          NOT NULL,
    PRIMARY KEY (`id_product`),
    FOREIGN KEY (`id_category`) REFERENCES `categories` (`id_category`)
);

CREATE TABLE `bills`
(
    `id_bill`   INT NOT NULL AUTO_INCREMENT,
    `date_bill` DATETIME DEFAULT now(),
    `detail`    LONGTEXT,
    `status`    BIT      DEFAULT 1, -- mặc định các hoá đơn được lưu đang tồn tại chưa bị ẩn đi
    `id`        INT NOT NULL,
    PRIMARY KEY (`id_bill`),
    FOREIGN KEY (`id`) REFERENCES `users` (`id`)
--    `id`        INT NOT NULL AUTO_INCREMENT,
--    `userId`    INT NOT NULL,
--    `details`   LONGTEXT,
--    `onGoing`   BOOLEAN DEFAULT TRUE, -- mặc định các hoá đơn được lưu đang tồn tại chưa bị ẩn đi
--    `createdAt` TIMESTAMP NOT NULL DEFAULT now(),
--    `updatedAt` TIMESTAMP NOT NULL DEFAULT now(),
--    PRIMARY KEY (`id`),
--    FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
);

CREATE TABLE `coupons`
(
    `id_coupons`       VARCHAR(10) NOT NULL, -- mã giảm giá sau này sẽ cho sinh tự nhiên có 10 ký tự bất kỳ
    `quantity_coupons` INT         NOT NULL,
    `cost`             LONG        NOT NULL,
    `finish_at`        DATETIME    NOT NULL,
    `start_at`         DATETIME    NOT NULL,
    PRIMARY KEY (`id_coupons`)
);


CREATE TABLE `items`
(
    `id_product` INT NOT NULL,
    `id_bill`    INT NOT NULL,
    `quantity`   INT DEFAULT 1,
    `note`       LONGTEXT,
    `price`      LONG, -- price của mỗi item là giá hiện tại của sản phẩm, trừ trường hợp giá của sản phẩm bị thay đổi mà cần truy vết hoá đơn
    PRIMARY KEY (`id_product`, `id_bill`),
    FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
    FOREIGN KEY (`id_bill`) REFERENCES `bills` (`id_bill`)
);


INSERT INTO `web_food`.`users` (name, email, password, phone, address, role, activate)
VALUES ('mai van hieu', 'maivanhieu@gmail.com', '123', '0705973063', 'nguyen hoang, my dinh', FALSE, DEFAULT);
INSERT INTO `web_food`.`users` (name, email, password, phone, address, role, activate)
VALUES ('admin', 'admin@gmail.com', 'admin', '0705973063', 'nguyen hoang, my dinh', DEFAULT, DEFAULT);
INSERT INTO `web_food`.`users` (name, email, password, phone, address, role, activate)
VALUES ('chu thi thuc trinh', 'trinh@gmail.com', '123', '0705973063', 'nguyen hoang, my dinh', DEFAULT, DEFAULT);
INSERT INTO `web_food`.`users` (name, email, password, phone, address, role, activate)
VALUES ('chu thi thuc anh', 'anh@gmail.com', '123', '0705973063', 'nguyen hoang, my dinh', DEFAULT, DEFAULT);
INSERT INTO `web_food`.`users` (name, email, password, phone, address, role, activate)
VALUES ('mai van ha', 'ha@gmail.com', '123', '0705973063', 'nguyen hoang, my dinh', FALSE, TRUE);


INSERT INTO `web_food`.`categories` (name_category, exist)
VALUES ('Rice', DEFAULT);
INSERT INTO `web_food`.`categories` (name_category, exist)
VALUES ('Noodles', DEFAULT);
INSERT INTO `web_food`.`categories` (name_category, exist)
VALUES ('Drinks', DEFAULT);
INSERT INTO `web_food`.`categories` (name_category, exist)
VALUES ('Fast product', DEFAULT);
INSERT INTO `web_food`.`categories` (name_category, exist)
VALUES ('Healthy', DEFAULT);


INSERT INTO `web_food`.`products` (name_product, price_product, image_product, description, available, id_category)
VALUES ('Com tho xa xiu', '45000',
        'https://images.foody.vn/res/g22/214720/s80x80/90ec487f-f28c-4b7f-d082-f33582f14064.jpg', 'description',
        DEFAULT, 1);
INSERT INTO `web_food`.`products` (name_product, price_product, image_product, description, available, id_category)
VALUES ('Bun tron nam bo', '40000',
        'https://images.foody.vn/res/g98/970725/s120x120/de563831-8cfb-4a7e-8bf4-439574ac-312b1c3e-221003093625.jpeg',
        'description', DEFAULT, 2);
INSERT INTO `web_food`.`products` (name_product, price_product, image_product, description, available, id_category)
VALUES ('Com tho xa xiu', '45000',
        'https://images.foody.vn/res/g22/214720/s80x80/90ec487f-f28c-4b7f-d082-f33582f14064.jpg', 'description',
        DEFAULT, 1);
INSERT INTO `web_food`.`products` (name_product, price_product, image_product, description, available, id_category)
VALUES ('Bun tron nam bo', '40000',
        'https://images.foody.vn/res/g98/970725/s120x120/de563831-8cfb-4a7e-8bf4-439574ac-312b1c3e-221003093625.jpeg',
        'description', DEFAULT, 2);
INSERT INTO `web_food`.`products` (name_product, price_product, image_product, description, available, id_category)
VALUES ('O LONG tao xanh nha dam', '25000',
        'https://images.foody.vn/res/g107/1063512/s120x120/cbb66518-bc28-4333-9939-b1d08ffc-e497ad94-220301140002.jpeg',
        'description', DEFAULT, 3);
INSERT INTO `web_food`.`products` (name_product, price_product, image_product, description, available, id_category)
VALUES ('Pizza hai san Pesto xanh', '169000',
        'https://thepizzacompany.vn/images/thumbs/000/0002624_seafood-pesto_300.png', 'description', DEFAULT, 4);
INSERT INTO `web_food`.`products` (name_product, price_product, image_product, description, available, id_category)
VALUES ('Com burger gao lut đo nhan ga', '55000',
        'https://images.foody.vn/res/g92/917956/s120x120/297afc01-69e2-46c0-bc91-8f102552-19c6ba46-210529170209.jpeg',
        'description', DEFAULT, 5);


INSERT INTO `web_food`.`bills` (id_bill, date_bill, detail, status, id)
VALUES (1, '2022-10-08 01:50:04', 'detail', DEFAULT, 1);
INSERT INTO `web_food`.`bills` (id_bill, date_bill, detail, status, id)
VALUES (2, '2022-10-08 01:50:04', 'detail', DEFAULT, 1);
INSERT INTO `web_food`.`bills` (id_bill, date_bill, detail, status, id)
VALUES (3, '2022-10-08 01:50:04', 'detail', DEFAULT, 2);
INSERT INTO `web_food`.`bills` (id_bill, date_bill, detail, status, id)
VALUES (4, '2022-10-08 01:50:04', 'detail', DEFAULT, 3);
INSERT INTO `web_food`.`bills` (date_bill, detail, status, id)
VALUES ('2022-10-08 01:50:04', 'detail', DEFAULT, 4);


INSERT INTO `web_food`.`items` (id_product, id_bill, quantity, note, price)
VALUES (2, 1, 2, 'note', NULL);
INSERT INTO `web_food`.`items` (id_product, id_bill, quantity, note, price)
VALUES (4, 1, 1, 'note', NULL);
INSERT INTO `web_food`.`items` (id_product, id_bill, quantity, note, price)
VALUES (5, 1, 1, 'note', NULL);
INSERT INTO `web_food`.`items` (id_product, id_bill, quantity, note, price)
VALUES (1, 2, 2, 'note', NULL);
INSERT INTO `web_food`.`items` (id_product, id_bill, quantity, note, price)
VALUES (2, 3, 1, 'note', NULL);
INSERT INTO `web_food`.`items` (id_product, id_bill, quantity, note, price)
VALUES (4, 4, 1, 'note', NULL);
INSERT INTO `web_food`.`items` (id_product, id_bill, quantity, note, price)
VALUES (5, 4, 2, 'note', NULL);
