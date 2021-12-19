INSERT INTO user (username, email, enabled, password, phone, avatar, created_at, updated_at)
VALUES('test', 'test@gmail.com', 1, '$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa', '0937441896', "", NOW(), NOW());

INSERT INTO payment (address_register, cvv, due, fullname, `number`, postal_code, `type`, user_id)
VALUES('504 Cách Mạng Tháng Tám, phường 11, Quận 3, Thành phố Hồ Chí Minh', 865, NOW(), 'Nguyễn Văn Test', '1234567890123456', 70000, 'VISA', 1);

INSERT INTO payment (address_register, cvv, due, fullname, `number`, postal_code, `type`, user_id)
VALUES('93 Lê Hoàn, Thanh Hóa', 272, NOW(), 'Mai Hường', '8593820481039402', 6969, 'MASTER_CARD', 1);
