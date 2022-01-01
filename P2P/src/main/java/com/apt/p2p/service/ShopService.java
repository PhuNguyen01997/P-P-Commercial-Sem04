package com.apt.p2p.service;

import com.apt.p2p.model.view.ShopModel;

public interface ShopService {
    public ShopModel findByProductId(int productId);
}
