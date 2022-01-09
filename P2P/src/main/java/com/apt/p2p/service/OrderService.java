package com.apt.p2p.service;

import com.apt.p2p.model.view.OrderModel;

public interface OrderService {
    public OrderModel create(OrderModel model);

    public OrderModel updateStatus(int statusId);
}
