package com.apt.p2p.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"Order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Boolean methodPayment;

    @NotNull
    private Double total;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusOrderId")
    private StatusOrder statusOrder;

    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "shopId")
    private List<Shop> shops;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentId")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderDeptId")
    private OrderDebt orderDebt;

    public Order(Boolean methodPayment, Double total, Date createdAt, Date updatedAt, User user, List<OrderDetail> orderDetails, List<Shop> shops, Address address, Payment payment, OrderDebt orderDebt) {
        this.methodPayment = methodPayment;
        this.total = total;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.orderDetails = orderDetails;
        this.shops = shops;
        this.address = address;
        this.payment = payment;
        this.orderDebt = orderDebt;
    }
}
