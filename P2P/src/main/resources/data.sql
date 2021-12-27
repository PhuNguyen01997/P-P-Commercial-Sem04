INSERT INTO user (avatar, created_at, email, enabled, password, phone, updated_at, username)
VALUES('', NOW(), 'seller@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0937441896', NOW(), 'seller');
INSERT INTO user (avatar, created_at, email, enabled, password, phone, updated_at, username)
VALUES('', NOW(), 'buyer@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0937441896', NOW(), 'buyer');
INSERT INTO user (avatar, created_at, email, enabled, password, phone, updated_at, username)
VALUES(NULL, NOW(), 'seller02@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0923823123', NOW(), 'seller02');

INSERT INTO payment (address_register, cvv, due, fullname, `number`, postal_code, `type`, user_id)
VALUES('504 Cách Mạng Tháng Tám, phường 11, Quận 3, Thành phố Hồ Chí Minh', 865, NOW(), 'Nguyễn Văn Test', '1234567890123456', 70000, 'VISA', 1);
INSERT INTO payment (address_register, cvv, due, fullname, `number`, postal_code, `type`, user_id)
VALUES('93 Lê Hoàn, Thanh Hóa', 272, NOW(), 'Mai Hường', '8593820481039402', 6969, 'MASTER_CARD', 1);

INSERT INTO address(district, `number`, province, ward, user_id)
VALUES('1', '123 Lê Lợi', 'TP. HCM', '2', 1);

INSERT INTO shop (created_at, logo, name, permission, phone, updated_at, address_id, payment_id, user_id)
VALUES(NOW(), 'no', 'Shốp Tếst 01', 1, '0907982932', NOW(), 1, 1, 1);
INSERT INTO shop (created_at, logo, name, permission, phone, updated_at, address_id, payment_id, user_id)
VALUES(NOW(), 'No', 'Shop Test 02', 1, '0497238647', NOW(), 1, 2, 3);

INSERT INTO category (created_at, name, updated_at)
VALUES(NOW(), 'Điện tử', NOW());
INSERT INTO category (created_at, name, updated_at)
VALUES(NOW(), 'Quần áo', NOW());
INSERT INTO category (created_at, name, updated_at)
VALUES(NOW(), 'Chất gây nghiện', NOW());
INSERT INTO category (created_at, name, updated_at)
VALUES(NOW(), 'Vũ khí', NOW());

INSERT INTO product (created_at, description, image, name, price, updated_at, category_id, shop_id)
VALUES(NOW(), 'Áo thun', '1.img', 'Áo thun', 200000.0, NOW(), 1, 1);
INSERT INTO product (created_at, description, image, name, price, updated_at, category_id, shop_id)
VALUES(NOW(), 'Quần dài', '1.img', 'Quần dài', 350000.0, NOW(), 1, 1);
INSERT INTO product (created_at, description, image, name, price, updated_at, category_id, shop_id)
VALUES(NOW(), 'Iphone13', '1.img', 'Iphone 13', 30000000, NOW(), 2, 1);
INSERT INTO product (created_at, description, image, name, price, updated_at, category_id, shop_id)
VALUES(NOW(), 'Ma túy đá', '1.img', 'Ma túy đá', 2900000.0, NOW(), 3, 2);
INSERT INTO product (created_at, description, image, name, price, updated_at, category_id, shop_id)
VALUES(NOW(), 'Mã tấu', '1.img', 'Mã tấu', 4200000.0, NOW(), 4, 2);

INSERT INTO rate (created_at, description, star, updated_at, product_id, user_id)
VALUES(NOW(), 'Hình ảnh và video chỉ mang tính chất nhận xu nhé. Hood giao nhanh lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2);
INSERT INTO rate (created_at, description, star, updated_at, product_id, user_id)
VALUES(NOW(), 'Mùa dịch nhưng giao hàng khá nhanh, chưa dùng nên chưa biết chất lượng thế nào', 2, NOW(), 2, 2);
INSERT INTO rate (created_at, description, star, updated_at, product_id, user_id)
VALUES(NOW(), 'Giao thiếu sp ( hơn 130 ngàn) . Nhắn tin ko trả lời. Làm ơn nếu đọc đc bình luận này thì trả lại tiền giúp đi. Bán hàng ko có tâm !', 1, NOW(), 3, 2);
INSERT INTO rate (created_at, description, star, updated_at, product_id, user_id)
VALUES(NOW(), 'Trong một đường tròn, đường kính vuông góc với một dây thì đi qua trung điểm của dây ấy. Do đó, trong một đường tròn, đường kính vuông góc với một dây thì đi qua trung điểm của dây ấy. Định lí 3: Trong một đường tròn, đường kính đi qua trung điểm của một dây không đi qua tâm thì vuông góc với dây ấy', 4, NOW(), 4, 2);
INSERT INTO rate (created_at, description, star, updated_at, product_id, user_id)
VALUES(NOW(), 'Vòng cũng OK nhưng vòng nó toàn đấy nhau thôi đeo mà tức kinh khủng luôn. Chúc shop mọi người nên tham khảo nhiều TRC khi mua nhá. Hình ảnh mang tính chất nhận xu', 3, NOW(), 5, 2);

INSERT INTO cart (created_at, quantity, updated_at, product_id, user_id)
VALUES(NOW(), 3, NOW(), 1, 2);
INSERT INTO cart (created_at, quantity, updated_at, product_id, user_id)
VALUES(NOW(), 2, NOW(), 2, 2);
INSERT INTO cart (created_at, quantity, updated_at, product_id, user_id)
VALUES(NOW(), 1, NOW(), 4, 2);
INSERT INTO cart (created_at, quantity, updated_at, product_id, user_id)
VALUES(NOW(), 1, NOW(), 5, 2);

