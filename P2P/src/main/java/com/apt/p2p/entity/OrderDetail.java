package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal lastPrice;

    @NotNull
    private Integer quantity;

    @Transient
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    public OrderDetail(BigDecimal lastPrice, Integer quantity) {
        this.lastPrice = lastPrice;
        this.quantity = quantity;
        this.subtotal = lastPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
