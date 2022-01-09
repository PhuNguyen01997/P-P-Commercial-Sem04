package com.apt.p2p.service;

import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderModel create(OrderModel model) {

    }

    @Override
    public OrderModel updateStatus(int statusId) {
        return null;
    }
}
