package com.apt.p2p.model.view;

import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.OrderDetail;
import com.apt.p2p.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailModel {
    private Integer id;

    private BigDecimal lastPrice;

    private Integer quantity;

    private BigDecimal subtotal;

    private ProductModel product;

    private OrderModel order;

    public OrderDetailModel(OrderDetail entity) {
        this.id = entity.getId();
        this.lastPrice = entity.getLastPrice();
        this.quantity = entity.getQuantity();
        this.subtotal = lastPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
