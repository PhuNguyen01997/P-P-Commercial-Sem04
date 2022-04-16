package com.apt.p2p.service;

import com.apt.p2p.model.view.ShopModel;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ShopService {
    List<ShopModel> findAll();
    ShopModel findById(int id);

    ShopModel findByProductId(int productId);

    ShopModel findByOrderId(int orderId);

    boolean shopWithDraw(int shopId, BigDecimal amount);

    ShopModel findByUserId(int userId);

    ShopModel createOrUpdate(ShopModel shopModel, MultipartFile logo, MultipartFile background);
}
