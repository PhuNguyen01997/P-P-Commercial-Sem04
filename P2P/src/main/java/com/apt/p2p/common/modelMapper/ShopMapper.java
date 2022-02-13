package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.ShopModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopMapper {
    @Autowired
    private MapperService mapperService;

    public Shop shopModelToEntity(ShopModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(ShopModel.class, Shop.class);
        mapper.addMappings(new PropertyMap<ShopModel, Shop>() {
            @Override
            protected void configure() {
                skip(destination.getUser());
                skip(destination.getProducts());
                skip(destination.getAddress());
                skip(destination.getOrders());
            }
        });

        mapper.validate();
        return mapper.map(model, Shop.class);
    }

    public ShopModel shopEntityToModel(Shop entity) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(Shop.class, ShopModel.class);
        mapper.addMappings(new PropertyMap<Shop, ShopModel>() {
            @Override
            protected void configure() {
                skip(destination.getUser());
                skip(destination.getOrders());
                skip(destination.getAddress());
                skip(destination.getProducts());
                skip(destination.getCountProducts());
                skip(destination.getCountRates());
            }
        });

        mapper.validate();
        ShopModel model = mapper.map(entity, ShopModel.class);

        model.setCountProducts(entity.getProducts().size());
        model.setCountRates(entity.getProducts().stream().map(product -> product.getRates().size()).reduce(0, Integer::sum));

        return model;
    }
}
