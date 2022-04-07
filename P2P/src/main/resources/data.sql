INSERT INTO user (avatar, created_at, email, enabled, password, phone, stripe_customer_id, updated_at, username) VALUES
('', NOW(), 'seller_01@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '8234789232', null, NOW(), 'seller01'),
(NULL, NOW(), 'seller_02@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0923823123', null,  NOW(), 'seller02'),
('', NOW(), 'buyer_01@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0937441896', null,  NOW(), 'buyer01'),
('', NOW(), 'buyer_02@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '9534728421', null,  NOW(), 'buyer02');

-- INSERT INTO payment (address_register, cvv, due, fullname, `number`, postal_code, `type`, user_id) VALUES
-- ('504 Cách Mạng Tháng Tám, phường 11, Quận 3, Thành phố Hồ Chí Minh', 865, NOW(), 'Seller 01', '1234567890123456', 70000, 'VISA', 1),
-- ('93 Lê Hoàn, Thanh Hóa', 272, NOW(), 'Mai Hường Seller 02', '8593820481039402', 6969, 'MASTER_CARD', 3),
-- ('237 SDAJ SDAKJH, phường 3, Quận 12, Thành phố Hồ Chí Minh', 743, NOW(), 'Buyer 01', '3895293053124463', 70000, 'VISA', 2),
-- ('355 UGHEDA SFE, phường 1, Quận 1, Thành phố Hồ Chí Minh', 743, NOW(), 'Buyer 02', '8567053029543134', 6437, 'VISA', 2);

-- INSERT INTO card (address_register, stripe_card_id, user_id) VALUES
-- ('504 Cách Mạng Tháng Tám, phường 11, Quận 3, Thành phố Hồ Chí Minh', 'CAR_KJFGHBKQ12312', 1);

INSERT INTO address (district, district_id, `number`, own_name, own_phone, province, province_id, ward, ward_id, user_id) VALUES
('Huyện Mường Khương', 2171, '123 ABC', 'Seller 01', '0937441896', 'Lào Cai', 269, 'Xã Thanh Bình', '80915', 1),
('Quận Ba Đình', 1484, '34 EWDS', 'Seller 02', '3478564738', 'Hà Nội', 201, 'Phường Giảng Võ', '1A0104', 2),
('Huyện Bình Đại', 1895, '123 CBX XYZ', 'Buyer 01', '093285435', 'Bến Tre', 213, 'Xã Thạnh Trị', '560615', 3),
('Quận 3', 1444, '123 EOIWT', 'Buyer 02', '9183473932', 'Hồ Chí Minh', 202, 'Phường 4', '20304', 3);

INSERT INTO shop (background, created_at, description, fund, logo, name, permission, phone, updated_at, address_id, stripe_card_id, user_id) VALUES
(null, NOW(), 'Mô tả shop 01', 0, null, 'Shốp Tếst 01', 1, '0907982932', NOW(), 1, "None", 1),
(null, NOW(), 'Mô tả shop 02', 0, null, 'Shop Test 02', 1, '0497238647', NOW(), 2, "None", 2);

INSERT INTO category (created_at, name, updated_at) VALUES
(NOW(), 'Điện tử', NOW()),
(NOW(), 'Quần áo', NOW()),
(NOW(), 'Chất gây nghiện', NOW()),
(NOW(), 'Vũ khí', NOW());

INSERT INTO product (created_at, description, image, name, price, updated_at, category_id, shop_id, stock) VALUES
(NOW(), 'Áo thun', '[]', 'Áo thun', 200000, NOW(), 1, 1, 0),
(NOW(), 'Quần dài', '[]', 'Quần dài', 350000, NOW(), 1, 1, 0),
(NOW(), 'Iphone13', '[]', 'Iphone 13', 5400000, NOW(), 2, 1, 0),
(NOW(), 'Ma túy đá', '[]', 'Ma túy đá', 2900000, NOW(), 3, 2, 0),
(NOW(), 'Mã tấu', '[]', 'Mã tấu', 100000, NOW(), 4, 2, 0),
(NOW(), 'Áo thun', '[]', 'Áo thun', 200000, NOW(), 1, 1, 0),
(NOW(), 'Quần dài', '[]', 'Quần dài', 350000, NOW(), 1, 1, 0),
(NOW(), 'Iphone13', '[]', 'Iphone 13', 5400000, NOW(), 2, 1, 0),
(NOW(), 'Ma túy đá', '[]', 'Ma túy đá', 2900000, NOW(), 3, 2, 0),
(NOW(), 'Mã tấu', '[]', 'Mã tấu', 100000, NOW(), 4, 2, 0),
(NOW(), 'Áo thun', '[]', 'Áo thun', 200000, NOW(), 1, 1, 0),
(NOW(), 'Quần dài', '[]', 'Quần dài', 350000, NOW(), 1, 1, 0),
(NOW(), 'Iphone13', '[]', 'Iphone 13', 5400000, NOW(), 2, 1, 0),
(NOW(), 'Ma túy đá', '[]', 'Ma túy đá', 2900000, NOW(), 3, 2, 0),
(NOW(), 'Mã tấu', '[]', 'Mã tấu', 100000, NOW(), 4, 2, 0);

INSERT INTO rate (created_at, description, star, updated_at, product_id, user_id) VALUES
(NOW(), 'Hình ảnh và video chỉ mang tính chất nhận xu nhé. Hood giao nhanh lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), 'Mùa dịch nhưng giao hàng khá nhanh, chưa dùng nên chưa biết chất lượng thế nào', 2, NOW(), 2, 2),
(NOW(), 'Giao thiếu sp ( hơn 130 ngàn) . Nhắn tin ko trả lời. Làm ơn nếu đọc đc bình luận này thì trả lại tiền giúp đi. Bán hàng ko có tâm !', 1, NOW(), 3, 2),
(NOW(), 'Trong một đường tròn, đường kính vuông góc với một dây thì đi qua trung điểm của dây ấy. Do đó, trong một đường tròn, đường kính vuông góc với một dây thì đi qua trung điểm của dây ấy. Định lí 3: Trong một đường tròn, đường kính đi qua trung điểm của một dây không đi qua tâm thì vuông góc với dây ấy', 4, NOW(), 4, 2),
(NOW(), 'Vòng cũng OK nhưng vòng nó toàn đấy nhau thôi đeo mà tức kinh khủng luôn. Chúc shop mọi người nên tham khảo nhiều TRC khi mua nhá. Hình ảnh mang tính chất nhận xu', 3, NOW(), 5, 2);

INSERT INTO cart (created_at, quantity, updated_at, product_id, user_id) VALUES
(NOW(), 3, NOW(), 1, 2),
(NOW(), 2, NOW(), 2, 2),
(NOW(), 1, NOW(), 4, 2),
(NOW(), 1, NOW(), 5, 2);

INSERT INTO status_order (id, done_name, name) VALUES
(1, 'Đã đặt hàng', 'Chờ xác nhận'),
(2, 'Đã thanh toán', 'Đã thanh toán'),
(3, 'Đã giao cho ĐVVC', 'Shop đang chuẩn bị hàng'),
(4, 'Đơn hàng đã nhận', 'Đang giao hàng'),
(5, 'Đánh giá', 'Đánh giá');
