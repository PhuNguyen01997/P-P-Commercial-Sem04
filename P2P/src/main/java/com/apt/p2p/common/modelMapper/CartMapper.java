package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.CartModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    @Autowired
    private MapperService mapperService;

    public Cart cartModelToEntity(CartModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(CartModel.class, Cart.class);
        mapper.addMappings(new PropertyMap<CartModel, Cart>() {
            @Override
            protected void configure() {
                skip(destination.getUser());
                skip(destination.getProduct());
            }
        });

        mapper.validate();
        return mapper.map(model, Cart.class);
    }

    public CartModel cartEntityToModel(Cart entity) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(Cart.class, CartModel.class);
        mapper.addMappings(new PropertyMap<Cart, CartModel>() {
            @Override
            protected void configure() {
                skip(destination.getUser());
                skip(destination.getProduct());
            }
        });

        mapper.validate();
        return mapper.map(entity, CartModel.class);
    }
}
