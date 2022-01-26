package com.apt.p2p.service;

import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderModel;

import java.util.List;

public interface OrderService {
    OrderModel create(PurchaseModel purchaseModel);

    OrderModel updateStatus(int statusId);

    List<OrderModel> findAllByUserId(int userId, boolean joinOrderDetailAndProduct, boolean joinShop);
}
