package com.apt.p2p.model.view;

import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrderDetailModel {
    private Integer id;

    private BigDecimal lastPrice;

    private Integer quantity;

    private BigDecimal subtotal;

    private Product product;

    private Order order;

}
