package com.example.demo.Entity;

import com.sun.istack.NotNull;


import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
public class OrderDetail {
    @Id

    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_Id")
    private Order orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_Id")
    private Product productId;
    @Column
    @NotNull
    private Double lastPrice;
    @Column
    @NotNull
    private Integer Quantity;
}
