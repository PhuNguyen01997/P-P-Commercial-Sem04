package com.apt.p2p.model.view;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class UserModel {
    private Integer id;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String phone;

    private String avatar;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private String stripeCustomerId;

//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
//    private Shop shop;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Address> addresses;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CardModel> payments;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Cart> carts;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Rate> rates;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Order> orders;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<OrderDebt> orderDebts;
}
