INSERT INTO role (role_id, name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_SELLER');

INSERT INTO category (created_at, name, updated_at) VALUES
(NOW(), 'Điện tử', NOW()),
(NOW(), 'Thời trang', NOW()),
(NOW(), 'Nội thất', NOW()),
(NOW(), 'Trang sức', NOW()),
(NOW(), 'Sắc đẹp', NOW()),
(NOW(), 'Thức ăn', NOW());

INSERT INTO status_order (id, done_name, name) VALUES
(1, 'Đã đặt hàng', 'Chờ xác nhận'),
(2, 'Đã thanh toán', 'Đã thanh toán'),
(3, 'Đã giao cho ĐVVC', 'Shop đang chuẩn bị hàng'),
(4, 'Đơn hàng đã giao', 'Đang giao hàng'),
(5, 'Đã nhận hàng', 'Nhận hàng'),
(6, 'Đánh giá', 'Đánh giá');

INSERT INTO user (avatar, created_at, email, enabled, password, phone, stripe_customer_id, sub_name, updated_at, username) VALUES
(NULL, NOW(), 'seller@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '8234789232', null, 'SubName', NOW(), 'seller'),
(NULL, NOW(), 'admin@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0937441896', null, 'SubName',  NOW(), 'admin'),
(NULL, NOW(), 'buyer@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0923823123', 'cus_LWVTKHJTZFu9W6', 'SubName',  NOW(), 'buyer');

INSERT INTO user_role (user_id, role_id) VALUES
(1, 1),
(1, 3),
(2, 1),
(2, 2),
(3, 1);


INSERT INTO address (district, district_id, `number`, own_name, own_phone, province, province_id, ward, ward_id, user_id) VALUES
('Huyện Mường Khương', 2171, '123 ABC', 'Seller 01', '0937441896', 'Lào Cai', 269, 'Xã Thanh Bình', '80915', 1),
('Quận Ba Đình', 1484, '34 EWDS', 'Seller 02', '3478564738', 'Hà Nội', 201, 'Phường Giảng Võ', '1A0104', 1),
('Huyện Bình Đại', 1895, '123 CBX XYZ', 'Buyer 01', '093285435', 'Bến Tre', 213, 'Xã Thạnh Trị', '560615', 1),
('Quận 3', 1444, '123 EOIWT', 'Buyer 02', '9183473932', 'Hồ Chí Minh', 202, 'Phường 4', '20304', 1);

INSERT INTO shop (background, created_at, description, fund, logo, name, permission, phone, updated_at, address_id, stripe_card_id, user_id) VALUES
('18_thumbnail.jpg', NOW(), '', 396042000, '18_logo.jpg', 'Shop Test', 1, '0907982932', NOW(), 2, "None", 1);

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
(NOW(), 'Màn hình LCD GLowy 17|19|22|24 inch - Mới 100% Full Box - Bảo hành chính hãng 24 tháng!', '[\"1_1151.jpg\",\"1_1152.jpg\"]', 'Màn hình LCD GLowy 17|19|22|24 inch - Mới 100% Full Box - Bảo hành chính hãng 24 tháng!', 2500000, NOW(), 1, 1, 0),
(NOW(), 'Bộ Bàn Phím Gaming có dây Kèm Chuột Máy Tính Con Báo Có LED 7 Màu Ấn Tượng', '[\"1_1153.jpg\",\"1_1154.jpg\",\"1_1155.jpg\"]', 'Bộ Bàn Phím Gaming có dây Kèm Chuột Máy Tính Con Báo Có LED 7 Màu Ấn Tượng', 134000, NOW(), 1, 1, 0),
(NOW(), 'Miếng dán màn hình HD cho Macbook ( tặng bộ khăn lâu và hỗ trợ dán màn chuyên nghiệp )', '[\"1_1156.jpg\",\"1_1157.jpg\",\"1_1158.jpg\"]', 'Miếng dán màn hình HD cho Macbook ( tặng bộ khăn lâu và hỗ trợ dán màn chuyên nghiệp )', 50000, NOW(), 1, 1, 0),
(NOW(), 'Chuột không dây Forter V181 bảo hành đổi mới trong 6 tháng', '[\"1_1159.jpg\",\"1_1160.jpg\",\"1_1161.jpg\"]', 'Chuột không dây Forter V181 bảo hành đổi mới trong 6 tháng', 41000, NOW(), 1, 1, 0),
(NOW(), 'Lót chuột cỡ lớn pad chuột freeship chuyên game siêu bền đẹp bảo hành 1 năm LC1', '[\"1_1162.jpg\",\"1_1163.jpg\",\"1_1164.jpg\"]', 'Lót chuột cỡ lớn pad chuột freeship chuyên game siêu bền đẹp bảo hành 1 năm LC1', 50000, NOW(), 1, 1, 0),
(NOW(), 'Quạt tản nhiệt điện thoại MEMO DL05 - Siêu lạnh, hiển thị nhiệt độ, LED RGB, Kẹp thu vào 2 chiều', '[\"1_1165.jpg\",\"1_1166.jpg\",\"1_1167.jpg\"]', 'Quạt tản nhiệt điện thoại MEMO DL05 - Siêu lạnh, hiển thị nhiệt độ, LED RGB, Kẹp thu vào 2 chiều', 380000, NOW(), 1, 1, 0),
(NOW(), 'Bộ 2 Găng Tay Cho Ngón Tay Bằng Vải Dệt Kim Chống Mồ Hôi Cho Tay Cầm Chơi Game', '[\"1_1168.jpg\",\"1_1169.jpg\",\"1_1170.jpg\"]', 'Bộ 2 Găng Tay Cho Ngón Tay Bằng Vải Dệt Kim Chống Mồ Hôi Cho Tay Cầm Chơi Game', 5000, NOW(), 1, 1, 0),
(NOW(), 'Kính cường lực Full màn 21D 6/6s/6plus/6s plus/7/8/7plus/8plus/x/xs/xs max/11/11/12/13/pro/promax', '[\"1_1171.jpg\",\"1_1172.jpg\",\"1_1173.jpg\"]', 'Kính cường lực Full màn 21D 6/6s/6plus/6s plus/7/8/7plus/8plus/x/xs/xs max/11/11/12/13/pro/promax', 12000, NOW(), 1, 1, 0),
(NOW(), 'Giá treo màn hình máy tính gắn bàn NB F80 (17-30 inch) - Xoay 360 độ, mẫu mới tải trọng 9kg - Hàng nhập khẩu', '[\"1_1174.jpg\",\"1_1175.jpg\",\"1_1176.jpg\"]', 'Giá treo màn hình máy tính gắn bàn NB F80 (17-30 inch) - Xoay 360 độ, mẫu mới tải trọng 9kg - Hàng nhập khẩu', 353000, NOW(), 1, 1, 0),
(NOW(), 'Loa bluetooth InPods LitteFun chính hãng Pass vòm 360 độ ghép đôi loa TWS âm thanh siêu hay', '[\"1_1177.jpg\",\"1_1178.jpg\",\"1_1179.jpg\"]', 'Loa bluetooth InPods LitteFun chính hãng Pass vòm 360 độ ghép đôi loa TWS âm thanh siêu hay', 129000, NOW(), 1, 1, 0),
(NOW(), 'Loa Bluetooth Mini Nghe Nhạc Hay Cầm Tay Nhỏ Gọn Có Móc Treo Giá Rẻ Hỗ Trợ Thẻ Nhớ Cổng 3.5mm - Gutek BS119', '[\"1_1180.jpg\",\"1_1181.jpg\",\"1_1182.jpg\"]', 'Loa Bluetooth Mini Nghe Nhạc Hay Cầm Tay Nhỏ Gọn Có Móc Treo Giá Rẻ Hỗ Trợ Thẻ Nhớ Cổng 3.5mm - Gutek BS119', 109000, NOW(), 1, 1, 0),
(NOW(), 'Sạc dự phòng 20000mAh PT126P-Max Sạc nhanh 2.1A màn hình kỹ thuật số LED - Bảo hành 12 tháng', '[\"1_1183.jpg\",\"1_1184.jpg\",\"1_1185.jpg\"]', 'Sạc dự phòng 20000mAh PT126P-Max Sạc nhanh 2.1A màn hình kỹ thuật số LED - Bảo hành 12 tháng', 199000, NOW(), 1, 1, 0),
(NOW(), 'Sạc nhanh PD 18w - 20w bộ củ cáp sạt dùng cho iphone ip 6/7/7plus/8/x/11/12, cóc xạc cổng typec to lightning dây dài 1m', '[\"1_1186.jpg\",\"1_1187.jpg\",\"1_1188.jpg\"]', 'Sạc nhanh PD 18w - 20w bộ củ cáp sạt dùng cho iphone ip 6/7/7plus/8/x/11/12, cóc xạc cổng typec to lightning dây dài 1m', 99000, NOW(), 1, 1, 0),
(NOW(), 'Dây sạc samsung, cổng Micro USB, Cáp sạc oppo, xiaomi... thiết kế dây bện dù cho điện thoại androi', '[\"1_1189.jpg\",\"1_1190.jpg\",\"1_1191.jpg\"]', 'Dây sạc samsung, cổng Micro USB, Cáp sạc oppo, xiaomi... thiết kế dây bện dù cho điện thoại androi', 15000, NOW(), 1, 1, 0),
(NOW(), 'Nồi chiên không dầu dung tích 5 lít CAMEL Bảo hành 6 tháng', '[\"1_1192.jpg\",\"1_1193.jpg\",\"1_1194.jpg\"]', 'Nồi chiên không dầu dung tích 5 lít CAMEL Bảo hành 6 tháng', 552000, NOW(), 1, 1, 0),
(NOW(), 'Lò nướng điện Comet CM6510 10L', '[\"1_1195.jpg\",\"1_1196.jpg\"]', 'Lò nướng điện Comet CM6510 10L', 485000, NOW(), 1, 1, 0);
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
(NOW(), 'Áo thun tay lỡ nam nữ, áo phông unisex form rộng chất cotton mát mịn thấm hút mồ hôi in hình MILK', '[\"2_6.jpg\",\"2_5.jpg\",\"2_4.jpg\"]', '', 39000, NOW(), 2, 1, 0),
(NOW(), 'Áo Croptop thun nữ kiểu yếm ôm hở lưng Xinh 24 sexy phong cách Hàn quốc', '[\"2_55.jpg\",\"2_56.jpg\",\"2_57.jpg\"]', '', 99000, NOW(), 2, 1, 0),
(NOW(), 'Áo Croptop Ngắn Tay Cổ Yếm 2 Màu Đen - Trắng Form Nữ Phong Cách Hàn Quốc', '[\"2_58.jpg\",\"2_59.jpg\",\"2_60.jpg\"]', '', 49000, NOW(), 2, 1, 0),
(NOW(), 'Túi xách đeo chéo nữ mini đựng điện thoại màu sắc basic dễ phối đồ phong cách hàn quốc giá rẻ đẹp', '[\"2_61.jpg\",\"2_62.jpg\",\"2_63.jpg\"]', '', 400000, NOW(), 2, 1, 0),
(NOW(), 'Túi xách nữ công sở đeo chéo đeo vai tote da thời trang cao cấp giá rẻ', '[\"2_64.jpg\",\"2_65.jpg\",\"2_66.jpg\"]', '', 250000, NOW(), 2, 1, 0),
(NOW(), 'Túi xách nữ 💖 𝑭𝑹𝑬𝑬𝑺𝑯𝑰𝑷 💖 Túi mini nữ đeo chéo hàng quảng châu siêu hot TD09', '[\"2_67.jpg\",\"2_68.jpg\",\"2_69.jpg\"]', '', 150000, NOW(), 2, 1, 0),
(NOW(), 'Dép nam, nữ siêu nhẹ DUWA - Hàng chính hãng - Bò sữa quai ngang DH1163', '[\"2_70.jpg\",\"2_71.jpg\",\"2_72.jpg\"]', '', 79000, NOW(), 2, 1, 0),
(NOW(), 'Dép đi biển nữ quai ngang mùa hè phiên bản Hàn Quốc đế dày chống trượt, T111', '[\"2_73.jpg\",\"2_74.jpg\",\"2_75.jpg\"]', '', 85000, NOW(), 2, 1, 0),
(NOW(), 'Váy Babydoll Vay Bồng Đũi Tơ 2 Lớp, Đầm Công Chúa Cực Xinh', '[\"2_76.jpg\",\"2_77.jpg\",\"2_78.jpg\"]', '', 350000, NOW(), 2, 1, 0),
(NOW(), 'Váy babydoll tay bồng cổ vuông Đầm trễ vai tiểu thư ulzzang dáng xoè hai lớp có khóa lưng basic đen trắng vintage', '[\"2_79.jpg\",\"2_80.jpg\",\"2_81.jpg\"]', '', 200000, NOW(), 2, 1, 0),
(NOW(), 'Váy ngủ 2 dây lụa satin cao cấp 4Lova mềm mịn, quyến rũ', '[\"2_82.jpg\",\"2_83.jpg\",\"2_84.jpg\"]', '', 150000, NOW(), 2, 1, 0),
(NOW(), 'SẴN🔥Váy lụa 2 dây cổ đổ phi lụa cao cấp', '[\"2_85.jpg\",\"2_86.jpg\",\"2_87.jpg\"]', '', 200000, NOW(), 2, 1, 0),
(NOW(), 'Áo croptop tay dài cổ bẻ siêu xịn', '[\"2_88.jpg\",\"2_89.jpg\",\"2_90.jpg\"]', '', 500000, NOW(), 2, 1, 0),
(NOW(), 'Dép Đế Mềm Chống Lạc Có Đèn Phát Sáng Hình Thỏ Dễ Thương Cho Bé', '[\"2_91.jpg\",\"2_92.jpg\",\"2_93.jpg\"]', '', 105000, NOW(), 2, 1, 0),
(NOW(), 'Dép Nữ Quai Ngang Thời Trang, Dép Lê Nữ MLB LA Cao Su Dập Nổi Hàng Đẹp 20SHOES', '[\"2_94.jpg\",\"2_95.jpg\",\"2_96.jpg\"]', '', 149000, NOW(), 2, 1, 0),
(NOW(), 'Bikini basic - Đồ bơi 2 mảnh siêu sang chảnh (nhiều màu)', '[\"2_97.jpg\",\"2_98.jpg\",\"2_99.jpg\"]', '', 1000000, NOW(), 2, 1, 0);
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
INSERT INTO product (created_at, name, image, description, price, updated_at, category_id, shop_id, stock) VALUES
(NOW(), 'Sa tế sò điệp Thích Cay,trộn mì, hủ tiếu, phở, sốt cá viên chiên, dùng để ướp thịt tiện lợi', '[\"1_11110.jpg\",\"1_11111.jpg\",\"1_11112.jpg\"]', '', 40000, NOW(), 6, 1, 0),
(NOW(), 'Mật ong nguyên chất hoa cà phê Beemo', '[\"1_11113.jpg\",\"1_11114.jpg\",\"1_11115.jpg\"]', '', 48500, NOW(), 6, 1, 0),
(NOW(), 'Mì xào khô/ mì trộn INDOMIE Goreng rẻ vô địch đủ vị', '[\"1_11116.jpg\",\"1_11117.jpg\",\"1_11118.jpg\"]', '', 5000, NOW(), 6, 1, 0),
(NOW(), 'Set Nguyên Liệu Trà Sữa Trân Châu 25-30 Ly Trà Thái Xanh, Đỏ, Phúc Long', '[\"1_11119.jpg\",\"1_11120.jpg\",\"1_11121.jpg\"]', '', 35000, NOW(), 6, 1, 0),
(NOW(), 'Yến mạch cán vỡ Hafer Flocken 500gr, 5 phút ăn liền', '[\"1_11122.jpg\",\"1_11123.jpg\",\"1_11124.jpg\"]', 'Y', 17000, NOW(), 6, 1, 0),
(NOW(), 'Ghẹ Sữa Rim Gia Vị 200G Đệ Nhất Khô đồ ăn vặt ngon giá rẻ Đặc Sản Phan Thiết', '[\"1_11125.jpg\",\"1_11126.jpg\",\"1_11127.jpg\"]', '', 59000, NOW(), 6, 1, 0),
(NOW(), 'Cơm cháy lắc khô bò 300g DumBum đồ ăn vặt Hà Nội vừa ngon vừa rẻ', '[\"1_11128.jpg\",\"1_11129.jpg\",\"1_11130.jpg\"]', '', 32000, NOW(), 6, 1, 0),
(NOW(), 'Bim Bim Cánh Gà Chiên Giòn ', '[\"1_11131.jpg\",\"1_11132.jpg\",\"1_11133.jpg\"]', '', 7000, NOW(), 6, 1, 0),
(NOW(), '150g Rong biển cháy tỏi loại ngon lon', '[\"1_11134.jpg\",\"1_11135.jpg\",\"1_11136.jpg\"]', '', 36000, NOW(), 6, 1, 0),
(NOW(), 'Chè Dưỡng Nhan Set Nguyên Liệu 30 Chén, Chè Tuyết Yến Bổ Dưỡng, Đẹp Da', '[\"1_11137.jpg\",\"1_11138.jpg\",\"1_11139.jpg\"]', '', 30000, NOW(), 6, 1, 0),
(NOW(), '500g Hạt Macca Organic Sạch Di Linh Lâm Đồng', '[\"1_11140.jpg\",\"1_11141.jpg\",\"1_11142.jpg\"]', '', 25000, NOW(), 6, 1, 0),
(NOW(), 'Kẹo Thạch Zaizai 320g / 350g / 500g - Đức Hạnh | Hương vị: Khoai Môn, Chanh Leo, Me, Dâu, Xoài, Đào, cam', '[\"1_11143.jpg\",\"1_11144.jpg\",\"1_11145.jpg\"]', '', 42000, NOW(), 6, 1, 0),
(NOW(), 'Bánh BISCOTTI Nguyên Cám Ăn Kiêng Lành Mạnh 100% by HeBe - Bánh Ngũ Cốc Ăn Kiêng Health', '[\"1_11146.jpg\",\"1_11147.jpg\",\"1_11148.jpg\"]', '', 45000, NOW(), 6, 1, 0),
(NOW(), 'ĐƯỜNG ĐEN TAIWAN', '[\"1_11149.jpg\",\"1_11150.jpg\",\"1_11151.jpg\"]', '', 98000, NOW(), 6, 1, 0),
(NOW(), 'Trái Đào Ngâm Nước Đường Thái Lan Lon 425gr & Lon 820gr', '[\"1_11152.jpg\",\"1_11153.jpg\",\"1_11154.jpg\"]', '', 24000, NOW(), 6, 1, 0);

-- INSERT INTO cart (created_at, quantity, updated_at, product_id, user_id) VALUES
-- (NOW(), 3, NOW(), 1, 2),
-- (NOW(), 2, NOW(), 2, 2),
-- (NOW(), 1, NOW(), 4, 2),
-- (NOW(), 1, NOW(), 5, 2);
