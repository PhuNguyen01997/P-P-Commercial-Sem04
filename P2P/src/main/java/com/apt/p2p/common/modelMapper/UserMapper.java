package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.*;
import com.apt.p2p.service.CardService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {
    @Autowired
    private MapperService mapperService;
    @Autowired
    private CardService cardService;

    public User userModelToEntity(UserModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(UserModel.class, User.class);
        mapper.addMappings(new PropertyMap<UserModel, User>() {
            @Override
            protected void configure() {
                skip(destination.getShop());
                skip(destination.getAddresses());
                skip(destination.getCarts());
                skip(destination.getRates());
                skip(destination.getOrders());
            }
        });

        mapper.validate();
        return mapper.map(model, User.class);
    }

    public UserModel userEntityToModel(User entity) {
        if(entity == null) return null;

        UserModel model = new UserModel(entity);

        model.setShop(entity.getShop() == null ? null : new ShopModel(entity.getShop()));
        model.setAddresses(entity.getAddresses().stream().map(ad -> new AddressModel(ad)).collect(Collectors.toList()));
        model.setCards(cardService.findAllByUserId(entity.getUserId()));
        model.setCarts(entity.getCarts().stream().map(c -> new CartModel(c)).collect(Collectors.toList()));
        model.setRates(entity.getRates().stream().map(r -> new RateModel(r)).collect(Collectors.toList()));
        model.setOrders(entity.getOrders().stream().map(o -> new OrderModel(o)).collect(Collectors.toList()));

        return model;
    }
}
