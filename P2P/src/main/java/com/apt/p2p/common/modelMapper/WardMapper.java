package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.Ward;
import com.apt.p2p.model.form.WardModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WardMapper {
    @Autowired
    private MapperService mapperService;
    @Autowired
    @Qualifier("setNullDestination")
    private Converter setNullDestination;

    public Ward wardModelToEntity(WardModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(WardModel.class, Ward.class);
        mapper.addMappings(new PropertyMap<WardModel, Ward>() {
            @Override
            protected void configure() {
                using(setNullDestination).map(source.getDistrict()).setDistrict(null);
            }
        });

        mapper.validate();
        return mapper.map(model, Ward.class);
    }

    public WardModel wardEntityToModel(Ward entity) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(Ward.class, WardModel.class);
        mapper.addMappings(new PropertyMap<Ward, WardModel>() {
            @Override
            protected void configure() {
                using(setNullDestination).map(source.getDistrict()).setDistrict(null);
            }
        });

        mapper.validate();
        return mapper.map(entity, WardModel.class);
    }
}
