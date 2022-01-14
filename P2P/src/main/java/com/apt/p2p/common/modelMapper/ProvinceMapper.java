package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.Province;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProvinceMapper {
    @Autowired
    private MapperService mapperService;
    @Autowired
    @Qualifier("setNullDestination")
    private Converter setNullDestination;

//    public Province provinceModelToEntity(ProvinceModel model) {
//        ModelMapper mapper = mapperService.getModelMapper();
//        mapper.typeMap(ProvinceModel.class, Province.class);
//        mapper.addMappings(new PropertyMap<ProvinceModel, Province>() {
//            @Override
//            protected void configure() {
//                using(setNullDestination).map(source.getDistricts()).setDistricts(null);
//            }
//        });
//
//        mapper.validate();
//        return mapper.map(model, Province.class);
//    }
//
//    public ProvinceModel provinceEntityToModel(Province entity) {
//        ModelMapper mapper = mapperService.getModelMapper();
//        mapper.typeMap(Province.class, ProvinceModel.class);
//        mapper.addMappings(new PropertyMap<Province, ProvinceModel>() {
//            @Override
//            protected void configure() {
//                using(setNullDestination).map(source.getDistricts()).setDistricts(null);
//            }
//        });
//
//        mapper.validate();
//        return mapper.map(entity, ProvinceModel.class);
//    }
}
