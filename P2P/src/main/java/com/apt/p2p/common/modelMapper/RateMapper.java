package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.modelview.RateModel;
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
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(Rate.class, RateModel.class);
        mapper.addMappings(new PropertyMap<Rate, RateModel>() {
            @Override
            protected void configure() {
                skip(destination.getUser());
                skip(destination.getProduct());
            }
        });

        mapper.validate();
        return mapper.map(entity, RateModel.class);
    }
}
