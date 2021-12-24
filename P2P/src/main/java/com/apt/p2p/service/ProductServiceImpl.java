package com.apt.p2p.service;

import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.model.ProductModel;
import com.apt.p2p.model.RateModel;
import com.apt.p2p.model.ShopModel;
import com.apt.p2p.repository.ProductRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.ValidationException;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    private Product modelMapEntity(ProductModel model) {
        try {
            ModelMapper mapper = new ModelMapper();
            mapper.typeMap(ProductModel.class, Product.class);
            mapper.addMappings(mapModelToEntity);
            mapper.validate();
            return mapper.map(model, Product.class);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private ProductModel entityMapModel(Product entity) {
        try {
            ModelMapper mapper = new ModelMapper();
            mapper.typeMap(Product.class, ProductModel.class);
            mapper.addMappings(mapEntityToModel);
            mapper.validate();
            return mapper.map(entity, ProductModel.class);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private PropertyMap<ProductModel, Product> mapModelToEntity = new PropertyMap<ProductModel, Product>() {
        @Override
        protected void configure() {
//            skip(destination.getShop());
//            skip(destination.getUser());
//            using(convertRemoveSpace).map(source.getCvv()).setCvv("error");
//            using(convertRemoveSpace).map(source.getNumber()).setNumber("error");
//            using(convertRemoveSpace).map(source.getPostalCode()).setPostalCode("error");
        }
    };

    private PropertyMap<Product, ProductModel> mapEntityToModel = new PropertyMap<Product, ProductModel>() {
        @Override
        protected void configure() {
            using(addPathImage).map(source.getImage()).setImage("");

            // Xử lý shop
            int id = source.getId();
//            Shop shopEntity = repository.findShopByProductId(source.getId());
//            ShopModel shopModel = new ShopModel(
//                    shopEntity.getId(),
//                    shopEntity.getLogo(),
//                    shopEntity.getPhone(),
//                    shopEntity.getPermission(),
//                    shopEntity.getCreatedAt(),
//                    shopEntity.getUpdatedAt(),
//                    null);

            // Xử lý rates
//            List<RateModel> ratesModel = repository.findRatesByProductId(source.getId()).stream()
//                    .map(rateE -> new RateModel(
//                            rateE.getId(),
//                            rateE.getDescription(),
//                            rateE.getStar(),
//                            rateE.getCreatedAt(),
//                            rateE.getUpdatedAt())
//                    ).collect(Collectors.toList());
//
//            map().setRates(ratesModel);
//            map().setShop(shopModel);
                String s = "dump";
//            skip(destination.getShop());
//            skip(destination.getRates());
//            using(convertAddSpaceNumber).map(source.getNumber()).setNumber("Error mapping");
        }
    };

    private Converter<String, String> addPathImage = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> mappingContext) {
            return "/images/product/" + mappingContext.getSource();
        }
    };

    @Override
    public ProductModel findById(int id) {
        ProductModel result = null;
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
//            result = entityMapModel(product.get());
            Product productEntity = product.get();
            productEntity.setShop(repository.findShopByProductId(productEntity.getId()));
            productEntity.setRate(repository.(productEntity.getId()));
        }
        return result;
    }
}
