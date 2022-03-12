package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.ProductMapper;
import com.apt.p2p.entity.Product;
import com.apt.p2p.model.form.FilterProductPortal;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.repository.ProductRepository;
import com.apt.p2p.repository.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductModel> findAllByShopId(int shopId) {
        return productRepository.findAllByShopId(shopId).stream().map(pe -> productMapper.productEntityToModel(pe)).collect(Collectors.toList());
    }

    @Override
    public ProductModel findByOrderDetailId(int orderDetailId) {
        return productMapper.productEntityToModel(productRepository.findByOrderDetailId(orderDetailId));
    }

    @Override
    public Integer countByShopId(int shopId) {
        return productRepository.countByShopId(shopId);
    }

    @Override
    public ProductModel findById(int id) {
        ProductModel result = null;
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            result = productMapper.productEntityToModel(product.get());
        }
        return result;
    }

    @Override
    public List<ProductModel> findAllByShopWithFilterPortal(int shopId, FilterProductPortal filter) {
        Specification<Product> condition = Specification.where(ProductSpecification.hasShopId(shopId));
        if(!filter.getName().isEmpty()){
            switch (filter.getFilterBy()) {
                case 1: {
                    condition = condition.and(ProductSpecification.hasName(filter.getName()));
                    break;
                }
                case 2: {
                    condition = condition.and(ProductSpecification.hasId(filter.getName()));
                    break;
                }
            }
        }

        if(filter.getCategoryId() != 0){
            condition = condition.and(ProductSpecification.hasCategoryId(filter.getCategoryId()));
        }

        switch (filter.getStatus()){
            case 1: {
                condition = condition.and(ProductSpecification.hasStock(true));
                break;
            }
            case 2: {
                condition = condition.and(ProductSpecification.hasStock(false));
                break;
            }
        }

        List<Product> productEntities = productRepository.findAll(condition);

        List<ProductModel> productModels = productEntities.stream()
                .map(e -> productMapper.productEntityToModel(e))
                .collect(Collectors.toList());

        return productModels;
    }
}
