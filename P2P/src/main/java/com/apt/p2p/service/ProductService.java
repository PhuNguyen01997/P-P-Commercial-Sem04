package com.apt.p2p.service;

import com.apt.p2p.model.view.ProductModel;

import java.util.List;

public interface ProductService {
    public ProductModel findById(int id);

    public Integer countByShopId(int shopId);

    List<ProductModel> findAllByShopId(int shopId);

    ProductModel findByOrderDetailId(int orderDetailId);
}
