package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.AddressModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(Address.class, AddressModel.class);
        mapper.addMappings(new PropertyMap<Address, AddressModel>() {
            @Override
            protected void configure() {
                skip(destination.getShop());
                skip(destination.getUser());
                skip(destination.getOrders());
            }
        });

        mapper.validate();
        return mapper.map(entity, AddressModel.class);
    }
}
