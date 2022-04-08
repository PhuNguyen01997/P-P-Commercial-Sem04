package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateMapper {
    @Autowired
    private MapperService mapperService;

    public Rate rateModelToEntity(RateModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(RateModel.class, Rate.class);
        mapper.addMappings(new PropertyMap<RateModel, Rate>() {
            @Override
            protected void configure() {
                skip(destination.getUser());
                skip(destination.getProduct());
            }
        });

        mapper.validate();
        return mapper.map(model, Rate.class);
    }

    public RateModel rateEntityToModel(Rate entity) {
        RateModel model = new RateModel(entity);

        model.setUser(new UserModel(entity.getUser()));
        model.setProduct(new ProductModel(entity.getProduct()));

        return model;
    }
}
