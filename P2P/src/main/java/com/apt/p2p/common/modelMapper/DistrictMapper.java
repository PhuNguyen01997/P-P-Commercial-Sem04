package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.District;
import com.apt.p2p.model.form.DistrictModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DistrictMapper {
    @Autowired
    private MapperService mapperService;
    @Autowired
    @Qualifier("setNullDestination")
    private Converter setNullDestination;

    public District districtModelToEntity(DistrictModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(DistrictModel.class, District.class);
        mapper.addMappings(new PropertyMap<DistrictModel, District>() {
            @Override
            protected void configure() {
                using(setNullDestination).map(source.getProvince()).setProvince(null);
                using(setNullDestination).map(source.getWards()).setWards(null);
            }
        });

        mapper.validate();
        return mapper.map(model, District.class);
    }

    public DistrictModel districtEntityToModel(District entity) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(District.class, DistrictModel.class);
        mapper.addMappings(new PropertyMap<District, DistrictModel>() {
            @Override
            protected void configure() {
                using(setNullDestination).map(source.getProvince()).setProvince(null);
                using(setNullDestination).map(source.getWards()).setWards(null);
            }
        });

        mapper.validate();
        return mapper.map(entity, DistrictModel.class);
    }
}
