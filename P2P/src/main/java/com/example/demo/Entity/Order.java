package com.example.demo.Entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderDept_Id")
    private OrderDept orderDeptId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusOrder_Id")
    private StatusOrder statusOrderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    private User userId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_Id")
    private Shop shopId;
    @Column
    @NotNull
    private Boolean methodPayment;
    @Column
    @NotNull
    private Double total;
    @Column
    @NotNull
    private Double percentPermission;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}
