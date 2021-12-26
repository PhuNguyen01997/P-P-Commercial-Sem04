package com.apt.p2p.service;

import com.apt.p2p.model.modelview.ProductModel;

public interface ProductService {
    public ProductModel findById(int id);

    public Integer countByShopId(int shopId);
}
