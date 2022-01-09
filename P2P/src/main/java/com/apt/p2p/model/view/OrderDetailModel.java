package com.apt.p2p.model.view;

import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class OrderDetailModel {
    private Integer id;

    private Double lastPrice;

    private Integer quantity;

    private Double subtotal;

    private Product product;

    private Order order;

}
