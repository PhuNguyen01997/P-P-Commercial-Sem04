package com.apt.p2p.service;

import com.apt.p2p.entity.Product;
import com.apt.p2p.model.form.FilterProductPortal;
import com.apt.p2p.model.view.ProductModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductModel findById(int id);

    Integer countByShopId(int shopId);

    List<ProductModel> findAllByShopId(int shopId);

    ProductModel findByOrderDetailId(int orderDetailId);

    List<ProductModel> findAllByShopWithFilterPortal(int shopId, FilterProductPortal filter);

    ProductModel create(ProductModel model, int categoryId);

    List<Product> SonFindAllWithFilter(String keyword, BigDecimal minPrice, BigDecimal maxPrice, Integer rate, String sortBy, Boolean sortDirection);
}
