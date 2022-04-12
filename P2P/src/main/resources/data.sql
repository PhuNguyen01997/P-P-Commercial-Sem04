INSERT INTO category (created_at, name, updated_at) VALUES
(NOW(), 'Điện tử', NOW()),
(NOW(), 'Thời trang', NOW()),
(NOW(), 'Gia dụng', NOW()),
(NOW(), 'Trang sức', NOW()),
(NOW(), 'Sắc đẹp', NOW()),
(NOW(), 'Thức ăn', NOW());

INSERT INTO status_order (id, done_name, name) VALUES
(1, 'Đã đặt hàng', 'Chờ xác nhận'),
(2, 'Đã thanh toán', 'Đã thanh toán'),
(3, 'Đã giao cho ĐVVC', 'Shop đang chuẩn bị hàng'),
(4, 'Đơn hàng đã nhận', 'Đang giao hàng'),
(5, 'Đánh giá', 'Đánh giá');

INSERT INTO user (avatar, created_at, email, enabled, password, phone, stripe_customer_id, updated_at, username) VALUES
(NULL, NOW(), 'seller@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '8234789232', null, NOW(), 'seller'),
(NULL, NOW(), 'buyer@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0923823123', null,  NOW(), 'buyer');

-- INSERT INTO payment (address_register, cvv, due, fullname, `number`, postal_code, `type`, user_id) VALUES
-- ('504 Cách Mạng Tháng Tám, phường 11, Quận 3, Thành phố Hồ Chí Minh', 865, NOW(), 'Seller 01', '1234567890123456', 70000, 'VISA', 1),
-- ('93 Lê Hoàn, Thanh Hóa', 272, NOW(), 'Mai Hường Seller 02', '8593820481039402', 6969, 'MASTER_CARD', 3),
-- ('237 SDAJ SDAKJH, phường 3, Quận 12, Thành phố Hồ Chí Minh', 743, NOW(), 'Buyer 01', '3895293053124463', 70000, 'VISA', 2),
-- ('355 UGHEDA SFE, phường 1, Quận 1, Thành phố Hồ Chí Minh', 743, NOW(), 'Buyer 02', '8567053029543134', 6437, 'VISA', 2);

-- INSERT INTO card (address_register, stripe_card_id, user_id) VALUES
-- ('504 Cách Mạng Tháng Tám, phường 11, Quận 3, Thành phố Hồ Chí Minh', 'CAR_KJFGHBKQ12312', 1);

INSERT INTO address (district, district_id, `number`, own_name, own_phone, province, province_id, ward, ward_id, user_id) VALUES
('Huyện Mường Khương', 2171, '123 ABC', 'Seller 01', '0937441896', 'Lào Cai', 269, 'Xã Thanh Bình', '80915', 1),
('Quận Ba Đình', 1484, '34 EWDS', 'Seller 02', '3478564738', 'Hà Nội', 201, 'Phường Giảng Võ', '1A0104', 1),
('Huyện Bình Đại', 1895, '123 CBX XYZ', 'Buyer 01', '093285435', 'Bến Tre', 213, 'Xã Thạnh Trị', '560615', 1),
('Quận 3', 1444, '123 EOIWT', 'Buyer 02', '9183473932', 'Hồ Chí Minh', 202, 'Phường 4', '20304', 1);

INSERT INTO shop (background, created_at, description, fund, logo, name, permission, phone, updated_at, address_id, stripe_card_id, user_id) VALUES
('18_thumbnail.jpg', NOW(), '', 0, '18_logo.jpg', 'Shop Test', 1, '0907982932', NOW(), 2, "None", 1);

INSERT INTO product (created_at, name, image, description, price, updated_at, category_id, shop_id, stock) VALUES
(NOW(), 'Vòng đá đeo tay từ tính 8mm chăm sóc sức khỏe độc đáo', '[\"1_18723.jpg\",\"1_34632.jpg\",\"1_4385.jpg\"]', '', 14000, NOW(), 4, 1, 0),
(NOW(), 'Vòng tay may mắn đính hạt ngọc trai phong cách Trung Hoa cổ điển cho nữ', '[\"4_7234.jpg\",\"4_745.jpg\",\"4_57.jpg\",\"4_4534.jpg\"]', '', 14000, NOW(), 4, 1, 0),
(NOW(), 'Lắc tay bạc cho bé gái hình nơ khắc tên LTT0042 - Trang Sức TNJ', '[\"4_546.jpg\",\"4_3485.jpg\"]', '', 319000, NOW(), 4, 1, 0),
(NOW(), 'Lắc tay xù cực đẹp, cao cấp', '[\"4_34543.jpg\",\"4_549.jpg\"]', '', 1300000, NOW(), 4, 1, 0),
(NOW(), '[Whiteline] Vòng tay Macrame Eye of Horus (có size Chân)', '[\"4_576.jpg\",\"4_54534.jpg\"]', '', 178200, NOW(), 4, 1, 0),
(NOW(), 'SET NHẪN BUTTERFLY', '[\"4_48695.jpg\",\"4_4368.jpg\",\"4_34987534.jpg\"]', '', 115000, NOW(), 4, 1, 0),
(NOW(), 'NHẪN NỮ MẶT ĐÁ CAO CẤP', '[\"4_47383.jpg\"]', '', 13000, NOW(), 4, 1, 0),
(NOW(), 'Đồng Hồ Samsung Galaxy Watch3 Bluetooth (45mm)', '[\"4_84937.jpg\",\"4_893453.jpg\",\"4_947354.jpg\"]', '', 6999999 , NOW(), 4, 1, 0),
(NOW(), 'Đồng Hồ Nữ Gucci Le Marche des Merveilles Watch YA1264060', '[\"4_38495743.jpg\",\"4_934543.jpg\",\"4_4387.jpg\"]', '', 7500000, NOW(), 4, 1, 0);
INSERT INTO product (created_at, name, image, description, price, updated_at, category_id, shop_id, stock) VALUES
(NOW(), '[WONTECH - NOW SHIP] Máy Laser Trục Khuỷu Yag Q-Switch RHINO - Siêu Phẩm Trị Nám, Tàn Nhang.', '[\"5_234543.jpg\",\"5_34978543.jpg\",\"5_398457.jpg\",\"5_348975.jpg\"]', '', 120000000, NOW(), 5, 1, 0),
(NOW(), '[CAM KẾT chất lượng tương đương 1200K] Đai Nịt Bụng Latex 25 Xương Nhập Khẩu Hi Lạp', '[\"5_389475.jpg\",\"5_3894765.jpg\"]', '', 259700, NOW(), 5, 1, 0),
(NOW(), 'N04 Mặt nạ quả bơ cấp ẩm mờ thâm dưỡng da làm sa mềm mịn cải thiện làn da khoẻ mạnh mặt nạ nội địa trung', '[\"5_5897436.jpg\",\"5_349875.jpg\",\"5_7667.jpg\",\"5_584679.jpg\"]', '', 2550, NOW(), 5, 1, 0),
(NOW(), 'Xịt khóa trang điểm cố định lớp make up Star Flash Spray 8 Hours Wear 100ML Sena Beauty', '[\"5_803546.jpg\",\"5_84576.jpg\",\"5_912324.jpg\",\"5_934685.jpg\",\"5_854736.jpg\",\"5_295823.jpg\"]', '', 33000, NOW(), 5, 1, 0),
(NOW(), 'Sữa Dưỡng Thể Dưỡng Trắng Da Toàn Thân Freshity Milky Body Lotion 250 gram', '[\"5_835768534.jpg\",\"5_4589067.jpg\",\"5_489567.jpg\"]', '', 339685, NOW(), 5, 1, 0),
(NOW(), '[NEW-Ver 2] Son kem lì trà sữa, lâu trôi, Hàn Quốc Romand Milk Tea Velvet Tint 4.4g', '[\"5_049568.jpg\",\"5_839745.jpg\",\"5_93456.jpg\",\"5_593467.jpg\",\"5_38476.jpg\"]', '', 239000, NOW(), 5, 1, 0),
(NOW(), 'Bộ đôi Sữa dưỡng thể trắng da Ngày (350ml) & Đêm (350ml) NIVEA (88311+88126)', '[\"5_4905786.jpg\",\"5_384673.jpg\",\"5_23897543.jpg\",\"5_8934567.jpg\",\"5_9384576.jpg\",\"5_09568.jpg\"]', '', 278000, NOW(), 5, 1, 0),
(NOW(), 'Một gói 50 tờ giấy thấm dầu ngẫu nhiên Sena Beauty', '[\"5_8345.jpg\",\"5_8345967.jpg\",\"5_8934576.jpg\",\"5_45897.jpg\",\"5_34987.jpg\"]', '', 6930, NOW(), 5, 1, 0),
(NOW(), 'Máy cạo râu Xiaomi Enchen BlackStone 3 Electric Shaver 3D', '[\"5_8943576.jpg\",\"5_83456.jpg\",\"5_3945.jpg\",\"5_839476.jpg\",\"5_853476.jpg\",\"5_23657.jpg\"]', '', 289900, NOW(), 5, 1, 0);
--
-- INSERT INTO rate (created_at, description, star, updated_at, product_id, user_id) VALUES
-- (NOW(), 'Hình ảnh và video chỉ mang tính chất nhận xu nhé. Hood giao nhanh lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
-- (NOW(), 'Mùa dịch nhưng giao hàng khá nhanh, chưa dùng nên chưa biết chất lượng thế nào', 2, NOW(), 2, 2),
-- (NOW(), 'Giao thiếu sp ( hơn 130 ngàn) . Nhắn tin ko trả lời. Làm ơn nếu đọc đc bình luận này thì trả lại tiền giúp đi. Bán hàng ko có tâm !', 1, NOW(), 3, 2),
-- (NOW(), 'Trong một đường tròn, đường kính vuông góc với một dây thì đi qua trung điểm của dây ấy. Do đó, trong một đường tròn, đường kính vuông góc với một dây thì đi qua trung điểm của dây ấy. Định lí 3: Trong một đường tròn, đường kính đi qua trung điểm của một dây không đi qua tâm thì vuông góc với dây ấy', 4, NOW(), 4, 2),
-- (NOW(), 'Vòng cũng OK nhưng vòng nó toàn đấy nhau thôi đeo mà tức kinh khủng luôn. Chúc shop mọi người nên tham khảo nhiều TRC khi mua nhá. Hình ảnh mang tính chất nhận xu', 3, NOW(), 5, 2);
--
-- INSERT INTO cart (created_at, quantity, updated_at, product_id, user_id) VALUES
-- (NOW(), 3, NOW(), 1, 2),
-- (NOW(), 2, NOW(), 2, 2),
-- (NOW(), 1, NOW(), 4, 2),
-- (NOW(), 1, NOW(), 5, 2);
