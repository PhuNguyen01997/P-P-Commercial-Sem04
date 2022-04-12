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

INSERT INTO product (created_at, description, image, name, price, updated_at, category_id, shop_id, stock) VALUES
(NOW(), 'Cây máy tính chơi game GTA LOL CF mới 99%', '[\"1_1111.jpg\",\"1_1112.jpg\"]', 'Cây máy tính chơi game GTA LOL CF mới 99%', 3950000, NOW(), 1, 1, 0),
(NOW(), 'Apple Watch Series 7 45mm GPS Sport Band', '[\"1_1113.jpg\",\"1_1114.jpg\"]', 'Apple Watch Series 7 45mm GPS Sport Band', 6500000, NOW(), 1, 1, 0),
(NOW(), 'Máy chơi game cầm tay F1 với 620 trò cho 2 người chơi thời gian chờ lên đến 6 tiếng', '[\"1_1115.jpg\",\"1_1116.jpg\"]', 'Máy chơi game cầm tay F1 với 620 trò cho 2 người chơi thời gian chờ lên đến 6 tiếng', 4000000, NOW(), 1, 1, 0),
(NOW(), 'Apple iPhone 13 Pro Max 128GB', '[\"1_1117.jpg\",\"1_1118.jpg\"]', 'Apple iPhone 13 Pro Max 128GB', 33000000, NOW(), 1, 1, 0),
(NOW(), 'Tai nghe Bluetooth nhét tai AirPods 2 Apple MV7N2', '[\"1_1119.jpg\",\"1_1120.jpg\"]', 'Tai nghe Bluetooth nhét tai AirPods 2 Apple MV7N2', 2500000, NOW(), 1, 1, 0),
(NOW(), 'Loa Bluetooth Xiaomi Mi Portable Bluetooth Speaker QBH4197GL/QBH4195GL MDZ-36-DB - Hàng chính hãng', '[\"1_1121.jpg\",\"1_1122.jpg\"]', 'Loa Bluetooth Xiaomi Mi Portable Bluetooth Speaker QBH4197GL/QBH4195GL MDZ-36-DB - Hàng chính hãng', 240000, NOW(), 1, 1, 0),
(NOW(), 'Smart TV HD Coocaa 32 Inch Wifi - 32S7G - Android 11', '[\"1_1123.jpg\",\"1_1124.jpg\"]', 'Smart TV HD Coocaa 32 Inch Wifi - 32S7G - Android 11', 7000000, NOW(), 1, 1, 0),
(NOW(), 'Tai Nghe Gaming Headphone HAVIT H2232D, Driver 50mm, Đèn Led RGB, Mic Khử Nhiễu - Chính Hãng BH 12 Tháng', '[\"1_1125.jpg\",\"1_1126.jpg\"]', 'Tai Nghe Gaming Headphone HAVIT H2232D, Driver 50mm, Đèn Led RGB, Mic Khử Nhiễu - Chính Hãng BH 12 Tháng', 350000, NOW(), 1, 1, 0),
(NOW(), 'Bộ máy tính PC Game + Màn FullHD 24 inch i7 /i5 /i3 chơi PUBG mobile, PUBG lite, LOL, CF đột kích, Fifa, Cs + Quà', '[\"1_1127.jpg\",\"1_1128.jpg\"]', 'Bộ máy tính PC Game + Màn FullHD 24 inch i7 /i5 /i3 chơi PUBG mobile, PUBG lite, LOL, CF đột kích, Fifa, Cs + Quà', 12000000, NOW(), 1, 1, 0),
(NOW(), 'Máy ảnh Canon G12', '[\"1_1129.jpg\",\"1_1130.jpg\"]', 'Máy ảnh Canon G12', 18500000, NOW(), 1, 1, 0),
(NOW(), 'Đồng Hồ Nam Nữ Chính Hãng NARY Dây Thép Thời Trang Cao Cấp Không Gỉ Mặt Đính Đá Đẹp Sang Trọng', '[\"1_1131.jpg\",\"1_1132.jpg\"]', 'Đồng Hồ Nam Nữ Chính Hãng NARY Dây Thép Thời Trang Cao Cấp Không Gỉ Mặt Đính Đá Đẹp Sang Trọng', 6000000, NOW(), 1, 1, 0),
(NOW(), 'Máy in phun màu đa năng Brother DCP-T420W', '[\"1_1133.jpg\",\"1_1134.jpg\"]', 'Máy in phun màu đa năng Brother DCP-T420W', 5000000, NOW(), 1, 1, 0),
(NOW(), 'Đầu Thu TV Thông Minh Pulierde H96 Mini Hỗ Trợ Android 9 1 / 2g Ddr3 8 / 16g Flash 4k Wifi 2.4 / 5g', '[\"1_1135.jpg\",\"1_1136.jpg\"]', 'Đầu Thu TV Thông Minh Pulierde H96 Mini Hỗ Trợ Android 9 1 / 2g Ddr3 8 / 16g Flash 4k Wifi 2.4 / 5g', 500000, NOW(), 1, 1, 0),
(NOW(), 'Tay cầm chơi game HOSAN gameSir T4 Pro, hỗ trợ cả PC và điện thoại, thời lượng pin 600mAh', '[\"1_1137.jpg\",\"1_1138.jpg\"]', 'Tay cầm chơi game HOSAN gameSir T4 Pro, hỗ trợ cả PC và điện thoại, thời lượng pin 600mAh', 200000, NOW(), 1, 1, 0),
(NOW(), 'Đĩa game SONY PS4 COD Morden Warfare PLAS-10485', '[\"1_1139.jpg\",\"1_1140.jpg\"]', 'Đĩa game SONY PS4 COD Morden Warfare PLAS-10485', 800000, NOW(), 1, 1, 0),
(NOW(), 'Camera giám sát an ninh IMOU SE IP không dây 2MP H.265 kết nối Wifi xoay tròn lên xuống trò chuyện 2 chiều', '[\"1_1141.jpg\",\"1_1142.jpg\"]', 'Camera giám sát an ninh IMOU SE IP không dây 2MP H.265 kết nối Wifi xoay tròn lên xuống trò chuyện 2 chiều', 3000000, NOW(), 1, 1, 0),
(NOW(), 'Flycam K99 MAX | MÁY BAY ĐIỀU KHIỂN K99 MAX | TRÁNH CHƯỚNG NGẠI VẬT | CAMERA 4K |', '[\"1_1143.jpg\",\"1_1144.jpg\"]', 'Flycam K99 MAX | MÁY BAY ĐIỀU KHIỂN K99 MAX | TRÁNH CHƯỚNG NGẠI VẬT | CAMERA 4K |', 17000000, NOW(), 1, 1, 0),
(NOW(), 'Giá đỡ tản nhiệt Laptop, Macbook, Ipad nhựa ABS chắc chắn, có thể gấp gọn điều chỉnh độ cao, đế tản nhiệt kê Laptop', '[\"1_1145.jpg\",\"1_1146.jpg\"]', 'Giá đỡ tản nhiệt Laptop, Macbook, Ipad nhựa ABS chắc chắn, có thể gấp gọn điều chỉnh độ cao, đế tản nhiệt kê Laptop', 150000, NOW(), 1, 1, 0),
(NOW(), 'Máy Chiếu FULL HD mini T01 Hệ Điều Hành Android, Kết Nối Điện Thoại, Nelfix', '[\"1_1147.jpg\",\"1_1148.jpg\"]', 'Máy Chiếu FULL HD mini T01 Hệ Điều Hành Android, Kết Nối Điện Thoại, Nelfix', 30000000, NOW(), 1, 1, 0),
(NOW(), 'Laptop Asus K43E (core i5-2450M/2GB/320GB/Intel HD3000/14"LED)', '[\"1_1149.jpg\",\"1_1150.jpg\"]', 'Laptop Asus K43E (core i5-2450M/2GB/320GB/Intel HD3000/14"LED)', 18000000, NOW(), 1, 1, 0),
(NOW(), 'Màn hình LCD GLowy 17|19|22|24 inch - Mới 100% Full Box - Bảo hành chính hãng 24 tháng!', '[\"1_1151.jpg\",\"1_1152.jpg\"]', 'Màn hình LCD GLowy 17|19|22|24 inch - Mới 100% Full Box - Bảo hành chính hãng 24 tháng!', 2500000, NOW(), 1, 1, 0);
INSERT INTO product (created_at, name, image, description, price, updated_at, category_id, shop_id, stock) VALUES
(NOW(), 'Áo thun planned farmer tay lỡ, áo phông nam nữ giá sốc QuynhNhiFashion', '[\"2_10.jpg\",\"2_11.jpg\",\"2_12.jpg\"]', '', 89000, NOW(), 2, 1, 0),
(NOW(), 'Áo thun nam nữ unisex tay lỡ Anime Bananashopz , áo phông form rộng cổ tròn siêu chất', '[\"2_7.jpg\",\"2_8.jpg\",\"2_9.jpg\"]', '', 79000, NOW(), 2, 1, 0),
(NOW(), 'Áo thun tay lỡ nam nữ 3nana, áo phông Unisex in chữ monster', '[\"2_1.jpg\",\"2_2.jpg\",\"2_3.jpg\"]', '', 500000, NOW(), 2, 1, 0),
(NOW(), '[ HÀNG SIÊU CẤP ]Dép bánh mỳ ,Dép jodan nam nữ đúc nguyên khối độn đế tăng chiều cao siêu êm chân', '[\"2_13.jpg\",\"2_14.jpg\",\"2_15.jpg\"]', '', 56000, NOW(), 2, 1, 0),
(NOW(), 'Dép bánh mì,dép quai ngang hình thỏ dễ thuơng loại 1 phong cách Ulzzang', '[\"2_16.jpg\",\"2_17.jpg\",\"2_18.jpg\"]', '', 456000, NOW(), 2, 1, 0),
(NOW(), 'Dép sục nữ quai nhún bèo đính ngọc', '[\"2_19.jpg\",\"2_20.jpg\",\"2_21.jpg\"]', '', 190000, NOW(), 2, 1, 0),
(NOW(), 'Dép 𝗠.𝗟.𝗕 𝗡𝗬 Quai Ngang Nam Nữ Unisex Siêu Hot Trend Chữ Thêu Sắc Nét Nhiều Màu, đủ size [ Fullbox + Hỗ trợ đổi size ]', '[\"2_22.jpg\",\"2_23.jpg\",\"2_24.jpg\"]', '', 852000, NOW(), 2, 1, 0),
(NOW(), 'Dép nữ quai ngang chanel hàng quảng châu cao cấp', '[\"2_25.jpg\",\"2_26.jpg\",\"2_27.jpg\"]', '', 789000, NOW(), 2, 1, 0),
(NOW(), 'VÁY TƠ BỒNG AMEE_TOI et MOI (100% ẢNH THẬT)', '[\"2_28.jpg\",\"2_29.jpg\",\"2_30.jpg\"]', '', 550000, NOW(), 2, 1, 0),
(NOW(), 'Váy Xoè Tiểu Thư Lưng Đan Dây Chéo Quyến Rũ HT24', '[\"2_31.jpg\",\"2_32.jpg\",\"2_33.jpg\"]', '', 229000, NOW(), 2, 1, 0),
(NOW(), 'QUẦN TẬP GYM NỮ ĐỒ TẬP YOGA NỮ QUẦN LEGGING TẬP GYM EO VÁT HERSIGN-LEGGING6', '[\"2_34.jpg\",\"2_35.jpg\",\"2_36.jpg\"]', '', 890000, NOW(), 2, 1, 0),
(NOW(), 'Quần ống loe HÀN QUỐC lên form chuẩn đẹp (Kèm ảnh thật)', '[\"2_37.jpg\",\"2_38.jpg\",\"2_39.jpg\"]', '', 104000, NOW(), 2, 1, 0),
(NOW(), 'Áo croptop cổ tròn BASIC 🌸🖤 viền cổ ngay ngắn sang xịn, thun Forever 21 co giãn 4 chiều êm, trắng đen da trơn CRON 1 -4D', '[\"2_40.jpg\",\"2_41.jpg\",\"2_42.jpg\"]', 'déc', 89000, NOW(), 2, 1, 0),
(NOW(), 'Áo Croptop Voan Trễ Vai Tay Phồng Đắp Chéo Phối Ren Cổ Bánh Bèo A501899', '[\"2_43.jpg\",\"2_44.jpg\",\"2_45.jpg\"]', '', 101000, NOW(), 2, 1, 0),
(NOW(), 'ÁO KHOÁC 2 MÀU CỰC XINH N01', '[\"2_46.jpg\",\"2_47.jpg\",\"2_48.jpg\"]', '', 178000, NOW(), 2, 1, 0),
(NOW(), 'ÁO KHOÁC NỈ TRƠN ULZZANG FORM RỘNG (ẢNH THẬT Ở CUỐI)', '[\"2_49.jpg\",\"2_50.jpg\",\"2_51.jpg\"]', '', 120000, NOW(), 2, 1, 0),
(NOW(), 'BIKINI UZZLANG HÀN QUỐC 2022 2 MẢNH CẠP CAO NƠ NGỰC', '[\"2_52.jpg\",\"2_53.jpg\",\"2_54.jpg\"]', '', 1000000, NOW(), 2, 1, 0),
(NOW(), 'Áo thun tay lỡ nam nữ, áo phông unisex form rộng chất cotton mát mịn thấm hút mồ hôi in hình MILK', '[\"2_6.jpg\",\"2_5.jpg\",\"2_4.jpg\"]', '', 39000, NOW(), 2, 1, 0);
INSERT INTO product (created_at, name, image, description, price, updated_at, category_id, shop_id, stock) VALUES
(NOW(), 'Vòng đá đeo tay từ tính 8mm chăm sóc sức khỏe độc đáo', '[\"4_18723.jpg\",\"4_34632.jpg\",\"4_4385.jpg\"]', '', 14000, NOW(), 4, 1, 0),
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
