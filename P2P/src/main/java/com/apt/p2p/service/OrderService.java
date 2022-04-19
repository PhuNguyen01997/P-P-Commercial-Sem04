package com.apt.p2p.service;

import com.apt.p2p.entity.Order;
import com.apt.p2p.model.form.FilterOrder;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    List<OrderModel> create(int userId, PurchaseModel purchaseModel);

    boolean updateStatus(int orderId, int statusId);

    List<OrderModel> findAllByUserId(int userId);

    Page<Order> findAllByUserId(int userId, PagiSortModel pagiSortModel);

    List<OrderModel> findALlByShopId(int shopId);

    List<OrderModel> findAllByShopIdWithFilter(int shopId, FilterOrder filterOrder);

    OrderModel findById(int orderId);
}
