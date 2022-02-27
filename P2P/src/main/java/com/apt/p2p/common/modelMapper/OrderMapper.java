package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                skip(destination.getUser());
                skip(destination.getOrderDetails());
                skip(destination.getShop());
                skip(destination.getAddress());
            }
        });

        mapper.validate();
        return mapper.map(model, Order.class);
    }

    public OrderModel orderEntityToModel(Order entity) {
        if(entity == null) return null;

        OrderModel result = new OrderModel();

        result.setId(entity.getId());
        result.setMethodPayment(entity.getMethodPayment());
        result.setTotal(entity.getTotal());
        result.setShippingCost(entity.getShippingCost());
        result.setPercentPermission(entity.getPercentPermission());
        result.setCreatedAt(entity.getCreatedAt());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setStatusHistories(entity.getStatusHistories());
        result.setCurrentStatus(entity.getCurrentStatus());

        result.setUser(new UserModel(entity.getUser()));
        result.setOrderDetails(entity.getOrderDetails().stream().map(ode -> new OrderDetailModel(ode)).collect(Collectors.toList()));
        result.setStatusHistories(entity.getStatusHistories().stream().map(sh -> new StatusHistory()).collect(Collectors.toList()));
        result.setShop(new ShopModel(entity.getShop()));
        result.setAddress(new AddressModel(entity.getAddress()));

        result.setPayment(null);

        return result;
    }
}
