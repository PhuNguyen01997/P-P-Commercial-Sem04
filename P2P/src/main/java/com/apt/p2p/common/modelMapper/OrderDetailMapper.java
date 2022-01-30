package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.OrderDetailModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.math.BigDecimal;

@Service
public class OrderDetailMapper {
    @Autowired
    private MapperService mapperService;

    public OrderDetail orderDetailModelToEntity(OrderDetailModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(OrderDetailModel.class, OrderDetail.class);
        mapper.addMappings(new PropertyMap<OrderDetailModel, OrderDetail>() {
            @Override
            protected void configure() {
                skip(source.getOrder());
                skip(source.getProduct());
            }
        });

        mapper.validate();
        return mapper.map(model, OrderDetail.class);
    }

    public OrderDetailModel orderDetailEntityToModel(OrderDetail entity) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(OrderDetail.class, OrderDetailModel.class);
        mapper.addMappings(new PropertyMap<OrderDetail, OrderDetailModel>() {
            @Override
            protected void configure() {
                skip(destination.getSubtotal());
                skip(destination.getOrder());
                skip(destination.getProduct());
            }
        });

        mapper.validate();
        OrderDetailModel model = mapper.map(entity, OrderDetailModel.class);

        model.setSubtotal(entity.getLastPrice().multiply(BigDecimal.valueOf(entity.getQuantity())));

        return model;
    }
}
