package com.apt.p2p.service;

import com.apt.p2p.model.view.ShopModel;

import java.math.BigDecimal;
import java.util.List;

public interface ShopService {
    ShopModel findById(int id);

    ShopModel findByProductId(int productId);

    ShopModel findByOrderId(int orderId);

    boolean shopWithDraw(int shopId, BigDecimal amount);
}
