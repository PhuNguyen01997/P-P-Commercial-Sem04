package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"Order\"")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Boolean methodPayment;

    @NotNull
    private BigDecimal total;

    @NotNull
    private BigDecimal shippingCost;

    @NotNull
    private Double percentPermission;

    private Date createdAt;

    private Date updatedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderStatusOrder> orderStatusOrders;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private Address address;

    private String stripeCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopFundId")
    private ShopFund shopFund;

    public Order(Boolean methodPayment, BigDecimal total, BigDecimal shippingCost, User user, List<OrderDetail> orderDetails, Shop shop, Address address, String stripeCardId, ShopFund shopFund) {
        this.methodPayment = methodPayment;
        this.total = total;
        this.shippingCost = shippingCost;
        this.percentPermission = 0.05;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.user = user;
        this.orderDetails = orderDetails;
        this.shop = shop;
        this.address = address;
        this.stripeCardId = stripeCardId;
        this.shopFund = shopFund;
    }
}
