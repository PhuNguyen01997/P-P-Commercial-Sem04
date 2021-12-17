package com.apt.p2p.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    @NotNull
    private String logo;

    @Column(length = 14)
    @NotNull
    private String phone;

    private Boolean permission = false;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentId")
    private Payment payment;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Rate> rates;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Order> orders;
}