package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.OrderModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
                skip(source.getCurrentStatus());
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
                skip(destination.getCurrentStatus());
            }
        });

        mapper.validate();
        OrderModel result = mapper.map(entity, OrderModel.class);

        StatusOrder currentStatus = entity.getOrderStatusOrders()
                .stream().sorted(Comparator.comparing(OrderStatusOrder::getId))
                .collect(Collectors.toList())
                .get(0).getStatus();
        result.setCurrentStatus(currentStatus);

        return result;
    }
}
