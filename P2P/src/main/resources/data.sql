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
(NOW(), 'Thời trang', NOW()),
(NOW(), 'Gia dụng', NOW()),
(NOW(), 'Trang sức', NOW()),
(NOW(), 'Sắc đẹp', NOW()),
(NOW(), 'Thức ăn', NOW());

INSERT INTO product (created_at, name, image, description, price, updated_at, category_id, shop_id, stock) VALUES
(NOW(), 'Vòng đá đeo tay từ tính 8mm chăm sóc sức khỏe độc đáo', '[\"1_18723.jpg\",\"1_34632.jpg\",\"1_4385.jpg\"]', 'Thời gian giao hàng dự kiến cho sản phẩm này là từ 7-9 ngày', 14000, NOW(), 4, 1, 0),
(NOW(), 'Vòng tay may mắn đính hạt ngọc trai phong cách Trung Hoa cổ điển cho nữ', '[\"4_7234.jpg\",\"4_745.jpg\",\"4_57.jpg\",\"4_4534.jpg\"]', 'Thời gian giao hàng dự kiến cho sản phẩm này là từ 7-9 ngày', 14000, NOW(), 4, 1, 0),
(NOW(), 'Lắc tay bạc cho bé gái hình nơ khắc tên LTT0042 - Trang Sức TNJ', '[\"4_546.jpg\",\"4_3485.jpg\"]', 'Lắc tay bạc cho bé hình nơ khắc tên LTT0042 - Trang Sức TNJ', 319000, NOW(), 4, 1, 0),
(NOW(), 'Lắc tay xù cực đẹp, cao cấp', '[\"4_34543.jpg\",\"4_549.jpg\"]', 'Không chỉ là quà tặng thông thường mà còn là thay ngàn lời muốn nói💟 👉 Gắn kết tình cảm gia đình, trao gửi yêu thương với các tuyệt phẩm quà tặng vô cùng độc đáo, ý nghĩa ➡️ Bảo hành thu mua sản phẩm trọn đời. <Các gái tag nhẹ ấy của mình vào để nhắc khéo nhé 😉> ------------------------ Liên hệ shop ngay để sở hữu những món quà chất hơn nước cất này bằng cách ⚜️Để lại cmt hoặc nhắn tin tới shop để được tư vấn cụ thể ☎️ Phone: 0979465921TUYỂN CỘNG TÁC VIÊN LIÊN TỤC CHIẾC KHẤU CAO', 1300000, NOW(), 4, 1, 0),
(NOW(), '[Whiteline] Vòng tay Macrame Eye of Horus (có size Chân)', '[\"4_576.jpg\",\"4_54534.jpg\"]', 'Size S: Dưới 45kg', 178200, NOW(), 4, 1, 0),
(NOW(), 'SET NHẪN BUTTERFLY', '[\"4_48695.jpg\",\"4_4368.jpg\",\"4_34987534.jpg\"]', 'Butterfly tượng trưng cho sự mơ mộng, lãng mạn- những ước mơ hồn nhiên, tươi sáng - biểu tượng của sự thay đổi cuộc đời theo chiều hướng tốt đẹp hơn.
Mẫu nhẫn gồm 2 nhẫn ghép (không bán lẻ) có thể đeo mix hay đeo rời đều xinh 🤍', 115000, NOW(), 4, 1, 0),
(NOW(), 'NHẪN NỮ MẶT ĐÁ CAO CẤP', '[\"4_47383.jpg\"]', '5 LÝ DO YÊN TÂM MUA HÀNG TẠI SHOP:', 13000, NOW(), 4, 1, 0),
(NOW(), 'Đồng Hồ Samsung Galaxy Watch3 Bluetooth (45mm)', '[\"4_84937.jpg\",\"4_893453.jpg\",\"4_947354.jpg\"]', 'Cảm biến: Accelerometer, Barometer, Gyro Sensor, Light Sensor, Optical Heart Rate Sensor', 6999999 , NOW(), 4, 1, 0),
(NOW(), 'Đồng Hồ Nữ Gucci Le Marche des Merveilles Watch YA1264060', '[\"4_38495743.jpg\",\"4_934543.jpg\",\"4_4387.jpg\"]', '✔ Thương hiệu: Gucci ', 7500000, NOW(), 4, 1, 0);


INSERT INTO rate (created_at, description, star, updated_at, product_id, user_id) VALUES
(NOW(), 'Hình ảnh và video chỉ mang tính chất nhận xu nhé. Hood giao nhanh lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '01 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '02 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '03 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '04 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '05 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '06 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '07 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '08 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '09 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '10 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '11 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '12 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
(NOW(), '13 lắm, 1 ngày là tới tay rồi. Lại chắc chắn, đóng gói cẩn thận.', 5, NOW(), 1, 2),
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
