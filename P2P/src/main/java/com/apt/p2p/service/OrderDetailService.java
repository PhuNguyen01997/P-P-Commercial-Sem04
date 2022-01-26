package com.apt.p2p.service;

import com.apt.p2p.model.view.OrderDetailModel;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailModel> findAllByOrderId(int orderId);
}
