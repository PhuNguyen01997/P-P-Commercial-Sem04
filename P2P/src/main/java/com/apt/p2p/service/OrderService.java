package com.apt.p2p.service;

import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderModel;

public interface OrderService {
    public OrderModel create(PurchaseModel purchaseModel);

    public OrderModel updateStatus(int statusId);
}
