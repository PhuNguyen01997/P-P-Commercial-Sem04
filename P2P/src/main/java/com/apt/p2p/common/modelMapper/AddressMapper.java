package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.model.view.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AddressMapper {
    @Autowired
    private MapperService mapperService;

    public Address addressModelToEntity(AddressModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(AddressModel.class, Address.class);
        mapper.addMappings(new PropertyMap<AddressModel, Address>() {
            @Override
            protected void configure() {
                skip(destination.getShop());
                skip(destination.getUser());
                skip(destination.getOrders());
            }
        });

        mapper.validate();
        return mapper.map(model, Address.class);
    }

    public AddressModel addressEntityToModel(Address entity) {
        AddressModel model = new AddressModel(entity);

        model.setUser(new UserModel(entity.getUser()));
        if(entity.getShop() != null){
            model.setShop(new ShopModel(entity.getShop()));
        }
        model.setOrders(entity.getOrders().stream().map(oe -> new OrderModel(oe)).collect(Collectors.toList()));

        return model;
    }
}
