package com.apt.p2p.entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @OneToOne
//    private User userId;

    @Column(length = 20)
    @NotNull
    private String logo;

    @Column(length = 14)
    @NotNull
    private String phone;

    private Boolean permission = false;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Product> products;
//
//    @OneToMany(mappedBy = "shopId", fetch = FetchType.LAZY)
//    private List<Rate> rates;
//
//    @OneToMany(mappedBy = "shopId", fetch = FetchType.LAZY)
//    private List<Order> orders;
}
