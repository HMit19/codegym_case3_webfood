CREATE DATABASE `web_food` CHARACTER SET 'utf8mb4' COLLATION 'utf8mb4_bin';

USE `web_food`;

CREATE TABLE `users`
(
    `id`        INT        NOT NULL AUTO_INCREMENT,
    `email`     TINYTEXT   NOT NULL UNIQUE,
    `password`  BINARY(64) NOT NULL,
    `name`      TINYTEXT   NOT NULL,
    `phone`     TINYTEXT   NOT NULL,
    `address`   TEXT       NOT NULL,
    `isAdmin`   BOOLEAN    NOT NULL,
    `isActive`  BOOLEAN    NOT NULL DEFAULT TRUE, -- mặc định khi tạo ra tài khoản sẽ ở trạng thái đang hoạt động
    `createdAt` TIMESTAMP  NOT NULL DEFAULT now(),
    `updatedAt` TIMESTAMP  NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`)
);

CREATE TABLE `categories`
(
    `id`          INT       NOT NULL AUTO_INCREMENT,
    `name`        TINYTEXT  NOT NULL,
    `description` TEXT,
    `isAvailable` BOOLEAN   NOT NULL DEFAULT TRUE, -- mặc định trạng thái của một doanh mục là tồn tại
    `createdAt`   TIMESTAMP NOT NULL DEFAULT now(),
    `updatedAt`   TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`)
);

CREATE TABLE `products`
(
    `id`          INT       NOT NULL AUTO_INCREMENT,
    `categoryId`  INT       NOT NULL,
    `name`        TINYTEXT  NOT NULL,
    `price`       INT       NOT NULL,
    `image`       TEXT,
    `description` TEXT      NOT NULL,
    `isAvailable` BOOLEAN   NOT NULL DEFAULT TRUE, -- mặc định các sản phẩm tạo mới đều có sẵn
    `isHidden`    BOOLEAN   NOT NULL DEFAULT FALSE, -- mặc định các sản phẩm tạo mới đều hiển thị
    `createdAt`   TIMESTAMP NOT NULL DEFAULT now(),
    `updatedAt`   TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`)
);

CREATE TABLE `bills`
(
    `id`        INT       NOT NULL AUTO_INCREMENT,
    `userId`    INT       NOT NULL,
    `notes`     TEXT,
    `isActive`  BOOLEAN   NOT NULL DEFAULT TRUE, -- mặc định các hoá đơn được lưu đang tồn tại chưa bị ẩn đi
    `createdAt` TIMESTAMP NOT NULL DEFAULT now(),
    `updatedAt` TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
);

--CREATE TABLE `coupons`
--(
--    `id_coupons`       VARCHAR(10) NOT NULL, -- mã giảm giá sau này sẽ cho sinh tự nhiên có 10 ký tự bất kỳ
--    `quantity_coupons` INT         NOT NULL,
--    `cost`             LONG        NOT NULL,
--    `finish_at`        DATETIME    NOT NULL,
--    `start_at`         DATETIME    NOT NULL,
--    PRIMARY KEY (`id_coupons`)
--);

CREATE TABLE `items`
(
    `billId`     INT       NOT NULL,
    `productId`  INT       NOT NULL,
    `quantity`   INT       NOT NULL,
    `notes`      TEXT,
    `price`      INT       NOT NULL, -- price của mỗi item là giá hiện tại của sản phẩm, trừ trường hợp giá của sản phẩm bị thay đổi mà cần truy vết hoá đơn
    `createdAt`  TIMESTAMP NOT NULL DEFAULT now(),
    `updatedAt`  TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`billId`, `productId`),
    FOREIGN KEY (`billId`) REFERENCES `bills` (`id`),
    FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
);

SET @randomBytes = RANDOM_BYTES(32);
INSERT INTO `web_food`.`users` (`email`, `password`, `salt`, `name`, `phone`, `address`, `isAdmin`)
VALUES ('admin@gmail.com',
		CONCAT(SHA2(CONCAT(@randomBytes, 'admin'), 256), @randomBytes), @randomBytes,
		'admin', '0705973063', 'nguyen hoang, my dinh', TRUE);
INSERT INTO `web_food`.`users` (`email`, `password`, `salt`, `name`, `phone`, `address`, `isAdmin`)
VALUES ('maivanhieu@gmail.com',
		SHA2(CONCAT(@randomBytes, '123', @randomBytes), 256), @randomBytes,
		'mai van hieu', '0705973063', 'nguyen hoang, my dinh', FALSE);
INSERT INTO `web_food`.`users` (`email`, `password`, `salt`, `name`, `phone`, `address`, `isAdmin`)
VALUES ('trinh@gmail.com',
		SHA2(CONCAT(@randomBytes, '123', @randomBytes), 256), @randomBytes,
		'chu thi thuc trinh', '0705973063', 'nguyen hoang, my dinh', FALSE);
INSERT INTO `web_food`.`users` (`email`, `password`, `salt`, `name`, `phone`, `address`, `isAdmin`)
VALUES ('anh@gmail.com',
		SHA2(CONCAT(@randomBytes, '123', @randomBytes), 256), @randomBytes,
		'chu thi thuc anh', '0705973063', 'nguyen hoang, my dinh', FALSE);
INSERT INTO `web_food`.`users` (`email`, `password`, `salt`, `name`, `phone`, `address`, `isAdmin`)
VALUES ('ha@gmail.com',
		SHA2(CONCAT(@randomBytes, '123', @randomBytes), 256), @randomBytes,
		'mai van ha', '0705973063', 'nguyen hoang, my dinh', FALSE);


INSERT INTO `web_food`.`categories` (`name`) VALUES ('Rice');
INSERT INTO `web_food`.`categories` (`name`) VALUES ('Noodles');
INSERT INTO `web_food`.`categories` (`name`) VALUES ('Drinks');
INSERT INTO `web_food`.`categories` (`name`) VALUES ('Fast product');
INSERT INTO `web_food`.`categories` (`name`) VALUES ('Healthy');

INSERT INTO `web_food`.`products` (`categoryId`, `name`, `price`, `image`, `description`)
VALUES (1, 'Com tho xa xiu', 45000,
        'https://images.foody.vn/res/g22/214720/s80x80/90ec487f-f28c-4b7f-d082-f33582f14064.jpg',
        'description 1');
INSERT INTO `web_food`.`products` (`categoryId`, `name`, `price`, `image`, `description`)
VALUES (2, 'Bun tron nam bo', 40000,
        'https://images.foody.vn/res/g98/970725/s120x120/de563831-8cfb-4a7e-8bf4-439574ac-312b1c3e-221003093625.jpeg',
        'description 2');
INSERT INTO `web_food`.`products` (`categoryId`, `name`, `price`, `image`, `description`)
VALUES (3, 'Com tho xa xiu', 45000,
        'https://images.foody.vn/res/g22/214720/s80x80/90ec487f-f28c-4b7f-d082-f33582f14064.jpg',
        'description 3');
INSERT INTO `web_food`.`products` (`categoryId`, `name`, `price`, `image`, `description`)
VALUES (2, 'Bun tron nam bo', 40000,
        'https://images.foody.vn/res/g98/970725/s120x120/de563831-8cfb-4a7e-8bf4-439574ac-312b1c3e-221003093625.jpeg',
        'description 4');
INSERT INTO `web_food`.`products` (`categoryId`, `name`, `price`, `image`, `description`)
VALUES (3, 'O LONG tao xanh nha dam', 25000,
        'https://images.foody.vn/res/g107/1063512/s120x120/cbb66518-bc28-4333-9939-b1d08ffc-e497ad94-220301140002.jpeg',
        'description 5');
INSERT INTO `web_food`.`products` (`categoryId`, `name`, `price`, `image`, `description`)
VALUES (4, 'Pizza hai san Pesto xanh', 169000,
        'https://thepizzacompany.vn/images/thumbs/000/0002624_seafood-pesto_300.png',
        'description 6');
INSERT INTO `web_food`.`products` (`categoryId`, `name`, `price`, `image`, `description`)
VALUES (5, 'Com burger gao lut đo nhan ga', 55000,
        'https://images.foody.vn/res/g92/917956/s120x120/297afc01-69e2-46c0-bc91-8f102552-19c6ba46-210529170209.jpeg',
        'description');


INSERT INTO `web_food`.`bills` (`userId`, `notes`, `isActive`)
VALUES (1, 'notes A', TRUE);
INSERT INTO `web_food`.`bills` (`userId`, `notes`, `isActive`)
VALUES (2, 'notes B', TRUE);
INSERT INTO `web_food`.`bills` (`userId`, `notes`, `isActive`)
VALUES (3, 'notes C', FALSE);
INSERT INTO `web_food`.`bills` (`userId`, `notes`, `isActive`)
VALUES (4, 'notes D', FALSE);
INSERT INTO `web_food`.`bills` (`userId`, `notes`, `isActive`)
VALUES (5, 'notes E', TRUE);

INSERT INTO `web_food`.`items` (`billId`, `productId`, `quantity`, `notes`, `price`)
VALUES (1, 2, 2, 'notes 1', 40000);
INSERT INTO `web_food`.`items` (`billId`, `productId`, `quantity`, `notes`, `price`)
VALUES (1, 4, 1, 'notes 2', 40000);
INSERT INTO `web_food`.`items` (`billId`, `productId`, `quantity`, `notes`, `price`)
VALUES (1, 5, 1, 'notes 3', 25000);
INSERT INTO `web_food`.`items` (`billId`, `productId`, `quantity`, `notes`, `price`)
VALUES (2, 1, 2, 'notes 4', 45000);
INSERT INTO `web_food`.`items` (`billId`, `productId`, `quantity`, `notes`, `price`)
VALUES (3, 2, 1, 'notes 5', 40000);
INSERT INTO `web_food`.`items` (`billId`, `productId`, `quantity`, `notes`, `price`)
VALUES (4, 6, 1, 'notes 6', 169000);
INSERT INTO `web_food`.`items` (`billId`, `productId`, `quantity`, `notes`, `price`)
VALUES (5, 7, 2, 'notes 7', 55000);
