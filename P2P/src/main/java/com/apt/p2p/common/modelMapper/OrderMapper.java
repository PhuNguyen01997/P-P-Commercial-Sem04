package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.OrderModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    @Autowired
    private MapperService mapperService;

    public Order orderModelToEntity(OrderModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(OrderModel.class, Order.class);
        mapper.addMappings(new PropertyMap<OrderModel, Order>() {
            @Override
            protected void configure() {
                skip(source.getUser());
                skip(source.getOrderDetails());
                skip(source.getShop());
                skip(source.getAddress());
                skip(source.getPayment());
                skip(source.getShopFund());
//                using(removeSpaceNumber).map(source.getCvv()).setCvv("error");
            }
        });

        mapper.validate();
        return mapper.map(model, Order.class);
    }

    public OrderModel orderEntityToModel(Order entity) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(Order.class, OrderModel.class);
        mapper.addMappings(new PropertyMap<Order, OrderModel>() {
            @Override
            protected void configure() {
                skip(destination.getUser());
                skip(destination.getOrderDetails());
                skip(destination.getShop());
                skip(destination.getAddress());
                skip(destination.getPayment());
                skip(destination.getShopFund());
            }
        });

        mapper.validate();
        return mapper.map(entity, OrderModel.class);
    }
}
