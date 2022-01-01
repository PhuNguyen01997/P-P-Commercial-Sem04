package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.ProductModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    @Autowired
    private MapperService mapperService;

    @Autowired
    @Qualifier("productAddPathImage")
    private Converter productAddPathImage;

    public Product productModelToEntity(ProductModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(ProductModel.class, Product.class);
        mapper.addMappings(new PropertyMap<ProductModel, Product>() {
            @Override
            protected void configure() {
                skip(destination.getShop());
                skip(destination.getOrderDetails());
                skip(destination.getCategory());
                skip(destination.getRates());
            }
        });

        mapper.validate();
        return mapper.map(model, Product.class);
    }

    public ProductModel productEntityToModel(Product entity) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(Product.class, ProductModel.class);
        mapper.addMappings(new PropertyMap<Product, ProductModel>() {
            @Override
            protected void configure() {
                skip(destination.getShop());
                skip(destination.getRates());
                using(productAddPathImage).map(source.getImage()).setImage("Error mapping images");
            }
        });

        mapper.validate();
        return mapper.map(entity, ProductModel.class);
    }
}
