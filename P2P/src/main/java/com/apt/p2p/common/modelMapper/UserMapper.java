package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    @Autowired
    private MapperService mapperService;

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
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(User.class, UserModel.class);
        mapper.addMappings(new PropertyMap<User, UserModel>() {
            @Override
            protected void configure() {
                skip(destination.getShop());
                skip(destination.getAddresses());
                skip(destination.getCarts());
                skip(destination.getCards());
                skip(destination.getRates());
                skip(destination.getOrders());
            }
        });

        mapper.validate();
        return mapper.map(entity, UserModel.class);
    }
}
