package com.apt.p2p.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ShopModel {
    private Integer id;

    @NotBlank
    private String logo;

    @NotBlank
    private String phone;

    private Boolean permission = false;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @NotNull
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId")
    private UserModel user;

//    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
//    private List<Product> products;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "addressId")
//    private Address address;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "paymentId")
//    private Payment payment;
//
//    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
//    private List<Rate> rates;
//
//    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
//    private List<Order> orders;
}
