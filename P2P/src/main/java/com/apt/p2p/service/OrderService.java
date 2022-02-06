package com.apt.p2p.service;

import com.apt.p2p.model.form.FilterOrder;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderModel;

import java.util.List;

public interface OrderService {
    List<OrderModel> create(PurchaseModel purchaseModel);

    OrderModel updateStatus(int statusId);

    List<OrderModel> findAllByUserId(int userId);

    List<OrderModel> findALlByShopId(int shopId);

    List<OrderModel> findAllWithFilter(FilterOrder filterOrder);

    OrderModel findById(int orderId);
}
