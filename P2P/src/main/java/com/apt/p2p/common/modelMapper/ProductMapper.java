package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ShopModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductMapper {
    @Autowired
    private MapperService mapperService;

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
        if(entity == null) return null;

        ProductModel model = new ProductModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setPrice(entity.getPrice());
        model.setImage(this.productAddPathImage(entity.getImage()));
        model.setDescription(entity.getDescription());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());

        model.setShop(new ShopModel(entity.getShop()));
        model.setRates(entity.getRates().stream().map(re -> new RateModel(re)).collect(Collectors.toList()));
        model.setCategory(entity.getCategory().removeRelationShip());

        return model;
    }

    private String productAddPathImage(String img){
        return "/images/product/" + img;
    }
}
