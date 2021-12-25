package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.ProductMapper;
import com.apt.p2p.common.modelMapper.ShopMapper;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.model.ProductModel;
import com.apt.p2p.model.RateModel;
import com.apt.p2p.model.ShopModel;
import com.apt.p2p.repository.ProductRepository;
import com.apt.p2p.repository.ShopRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.ValidationException;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired
    private ShopServiceImpl shopService;
    @Autowired
    private RateServiceImpl rateService;

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

            ShopModel shopModel = shopService.findByProductId(id);
            shopModel.setCountProducts(this.countByShopId(id));
//            shopModel.setCountRates(rateService.count);

            result.setShop(shopModel);
            result.setRates(rateService.findByProductId(id));
        }
        return result;
    }
}
