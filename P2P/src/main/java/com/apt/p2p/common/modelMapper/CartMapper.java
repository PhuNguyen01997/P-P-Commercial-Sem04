package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.CartModel;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.UserModel;
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
        if(entity == null) return null;

        CartModel model = new CartModel();

        model.setId(entity.getId());
        model.setQuantity(entity.getQuantity());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setUser(new UserModel(entity.getUser()));
        model.setProduct(new ProductModel(entity.getProduct()));

        return model;
    }
}
