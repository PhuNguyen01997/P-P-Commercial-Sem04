package com.apt.p2p.service;

import com.apt.p2p.entity.Product;
import com.apt.p2p.model.form.FilterProductIndex;
import com.apt.p2p.model.form.FilterProductPortal;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.form.ProductForm;
import com.apt.p2p.model.view.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductModel findById(int id);

    Integer countByShopId(int shopId);

    List<ProductModel> findAllByShopId(int shopId);

    ProductModel findByShopIdAndProductId(int shopId, int productId);

    ProductModel findByOrderDetailId(int orderDetailId);

    List<ProductModel> findAllByShopWithFilterPortal(int shopId, FilterProductPortal filter);

    ProductModel create(ProductForm productFormModel, int shopId);

    ProductModel update(ProductForm productFormModel);

    Page<Product> findAllByShopWithFilterIndex(FilterProductIndex filterModel, PagiSortModel pagiSortModel);
}
