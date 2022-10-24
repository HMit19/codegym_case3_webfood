create database WEBFOOD;

use WEBFOOD;

create table `USER`
(
    `id`       int          not null auto_increment,
    `name`     varchar(100) not null,
    `email`    varchar(100) not null unique,
    `password` varchar(100) not null,
    `phone`    varchar(20)  not null,
    `address`  varchar(150) not null,
    `role`     bit default 0, -- mặc định khi tài khoản mới được khởi tạo sẽ có quyền user
    `activate` BIT default 1, -- mặc định khi tạo ra tài khoản sẽ ở trạng thái đang hoạt động
    PRIMARY KEY (`id`)
);

CREATE TABLE `CATEGORY`
(
    `id_category`   iNT          not nuLl auto_increment,
    `name_category` varchar(150) not null unique,
    `exist`         bit default 1, -- mặc định trạng thái của một doanh mục là tồn tại
    PRIMARY KEY (`id_category`)
);

CREATE TABLE `PRODUCT`
(
    `id_product`    Int          NoT NULL auto_increment,
    `name_product`  varchar(150) NOT NUll,
    `price_product` long         nOT NULL,
    `image_product` varchar(350),
    `description`   longtext,
    `available`     bit default 1, -- mặc định các sản phẩm tạo mới đều có sẵn
    `id_category`   Int          NOT NULL,
    PRIMARY KEY (`id_product`),
    FOREIGN KEY (`id_category`) REFERENCES `caTegoRy` (`id_category`)
);

CREATE TABLe `BILL`
(
    `id_bill`   INT NOT nUll auto_increment,
    `date_bill` datetime DEFAULT now(),
    `detail`    longtext,
    `status`    bit      default 1, -- mặc định các hoá đơn được lưu đang tồn tại chưa bị ẩn đi
    `id`        INt nOt null,
    PRIMARY KEY (`id_bill`),
    FOREIGN KEY (`id`) REFERENCES USER (`id`)
);

CREATE TABLE `COUPONS`
(
    `id_coupons`       varchar(10) not null, -- mã giảm giá sau này sẽ cho sinh tự nhiên có 10 ký tự bất kỳ
    `quantity_coupons` int         not null,
    `cost`             long        NOT NULL,
    `finish_at`        datetime    NOT NULL,
    `start_at`         datetime    NOT NULL,
    PRIMARY KEY (`id_coupons`)
);


creatE TABLE `ITEM`
(
    `id_product` INT NOT null,
    `id_bill`    INT NOT NULL,
    `quantity`   int default 1,
    `note`       longtext,
    `price`      long, -- price của mỗi item là giá hiện tại của sản phẩm, trừ trường hợp giá của sản phẩm bị thay đổi mà cần truy vết hoá đơn
    priMARY KEY (`id_product`, `id_bill`),
    FOREIGN KEY (`id_product`) REFERENCES `PRODUCT` (`id_product`),
    FOREIGN KEY (`id_bill`) REFERENCES `BILL` (`id_bill`)
);


INSERT INTO webfood.user (name, email, password, phone, address, role, activate)
VALUES ('mai van hieu', 'maivanhieu@gmail.com', '123', '0705973063', 'nguyen hoang, my dinh', false, DEFAULT);
INSERT INTO webfood.user (name, email, password, phone, address, role, activate)
VALUES ('admin', 'admin@gmail.com', 'admin', '0705973063', 'nguyen hoang, my dinh', DEFAULT, DEFAULT);
INSERT INTO webfood.user (name, email, password, phone, address, role, activate)
VALUES ('chu thi thuc trinh', 'trinh@gmail.com', '123', '0705973063', 'nguyen hoang, my dinh', DEFAULT, DEFAULT);
INSERT INTO webfood.user (name, email, password, phone, address, role, activate)
VALUES ('chu thi thuc anh', 'anh@gmail.com', '123', '0705973063', 'nguyen hoang, my dinh', DEFAULT, DEFAULT);
INSERT INTO webfood.user (name, email, password, phone, address, role, activate)
VALUES ('mai van ha', 'ha@gmail.com', '123', '0705973063', 'nguyen hoang, my dinh', false, true);


INSERT INTO webfood.category (name_category, exist)
VALUES ('Rice', DEFAULT);
INSERT INTO webfood.category (name_category, exist)
VALUES ('Noodles', DEFAULT);
INSERT INTO webfood.category (name_category, exist)
VALUES ('Drinks', DEFAULT);
INSERT INTO webfood.category (name_category, exist)
VALUES ('Fast product', DEFAULT);
INSERT INTO webfood.category (name_category, exist)
VALUES ('Healthy', DEFAULT);


INSERT INTO webfood.product (name_product, price_product, image_product, description, available, id_category)
VALUES ('Com tho xa xiu', '45000',
        'https://images.foody.vn/res/g22/214720/s80x80/90ec487f-f28c-4b7f-d082-f33582f14064.jpg', 'description',
        DEFAULT, 1)
INSERT INTO webfood.product (name_product, price_product, image_product, description, available, id_category)
VALUES ('Bun tron nam bo', '40000',
        'https://images.foody.vn/res/g98/970725/s120x120/de563831-8cfb-4a7e-8bf4-439574ac-312b1c3e-221003093625.jpeg',
        'description', DEFAULT, 2)
INSERT INTO webfood.product (name_product, price_product, image_product, description, available, id_category)
VALUES ('Com tho xa xiu', '45000',
        'https://images.foody.vn/res/g22/214720/s80x80/90ec487f-f28c-4b7f-d082-f33582f14064.jpg', 'description',
        DEFAULT, 1)
INSERT INTO webfood.product (name_product, price_product, image_product, description, available, id_category)
VALUES ('Bun tron nam bo', '40000',
        'https://images.foody.vn/res/g98/970725/s120x120/de563831-8cfb-4a7e-8bf4-439574ac-312b1c3e-221003093625.jpeg',
        'description', DEFAULT, 2)
INSERT INTO webfood.product (name_product, price_product, image_product, description, available, id_category)
VALUES ('O long tao xanh nha dam', '25000',
        'https://images.foody.vn/res/g107/1063512/s120x120/cbb66518-bc28-4333-9939-b1d08ffc-e497ad94-220301140002.jpeg',
        'description', DEFAULT, 3)
INSERT INTO webfood.product (name_product, price_product, image_product, description, available, id_category)
VALUES ('Pizza hai san Pesto xanh', '169000',
        'https://thepizzacompany.vn/images/thumbs/000/0002624_seafood-pesto_300.png', 'description', DEFAULT, 4)
INSERT INTO webfood.product (name_product, price_product, image_product, description, available, id_category)
VALUES ('Com burger gao lut đo nhan ga', '55000',
        'https://images.foody.vn/res/g92/917956/s120x120/297afc01-69e2-46c0-bc91-8f102552-19c6ba46-210529170209.jpeg',
        'description', DEFAULT, 5)


INSERT INTO webfood.bill (id_bill, date_bill, detail, status, id)
VALUES (1, '2022-10-08 01:50:04', 'detail', DEFAULT, 1);
INSERT INTO webfood.bill (id_bill, date_bill, detail, status, id)
VALUES (2, '2022-10-08 01:50:04', 'detail', DEFAULT, 1);
INSERT INTO webfood.bill (id_bill, date_bill, detail, status, id)
VALUES (3, '2022-10-08 01:50:04', 'detail', DEFAULT, 2);
INSERT INTO webfood.bill (id_bill, date_bill, detail, status, id)
VALUES (4, '2022-10-08 01:50:04', 'detail', DEFAULT, 3);
INSERT INTO webfood.bill (date_bill, detail, status, id)
VALUES ('2022-10-08 01:50:04', 'detail', DEFAULT, 4);


INSERT INTO webfood.item (id_product, id_bill, quantity, note, price)
VALUES (2, 1, 2, 'note', null);
INSERT INTO webfood.item (id_product, id_bill, quantity, note, price)
VALUES (4, 1, 1, 'note', null);
INSERT INTO webfood.item (id_product, id_bill, quantity, note, price)
VALUES (5, 1, 1, 'note', null);
INSERT INTO webfood.item (id_product, id_bill, quantity, note, price)
VALUES (1, 2, 2, 'note', null);
INSERT INTO webfood.item (id_product, id_bill, quantity, note, price)
VALUES (2, 3, 1, 'note', null);
INSERT INTO webfood.item (id_product, id_bill, quantity, note, price)
VALUES (4, 4, 1, 'note', null);
INSERT INTO webfood.item (id_product, id_bill, quantity, note, price)
VALUES (5, 4, 2, 'note', null);
