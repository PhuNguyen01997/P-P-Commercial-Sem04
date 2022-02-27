package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
        if(entity == null) return null;

        ShopModel model = new ShopModel(entity);

        model.setUser(new UserModel(entity.getUser()));
        model.setAddress(new AddressModel(entity.getAddress()));

        model.setProducts(entity.getProducts().stream().map(e -> new ProductModel(e)).collect(Collectors.toList()));
        model.setOrders(entity.getOrders().stream().map(e -> new OrderModel(e)).collect(Collectors.toList()));
        model.setShopTransactions(entity.getShopTransactions().stream().map(e -> new ShopTransactionModel(e)).collect(Collectors.toList()));

        return model;
    }
}
