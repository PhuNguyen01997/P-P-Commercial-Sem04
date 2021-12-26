package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.ProductMapper;
import com.apt.p2p.entity.Product;
import com.apt.p2p.model.modelview.ProductModel;
import com.apt.p2p.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper productMapper;

    public List<ProductModel> findByShopId(int shopId){
        return repository.findByShopId(shopId).stream().map(pe -> productMapper.productEntityToModel(pe)).collect(Collectors.toList());
    }

    @Override
    public Integer countByShopId(int shopId) {
        return repository.countByShopId(shopId);
    }

    @Override
    public ProductModel findById(int id) {
        ProductModel result = null;
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            result = productMapper.productEntityToModel(product.get());
        }
        return result;
    }
}
