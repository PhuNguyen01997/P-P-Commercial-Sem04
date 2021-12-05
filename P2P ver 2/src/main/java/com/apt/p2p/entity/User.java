package com.apt.p2p.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String email;

    @NotNull
    private String username;

    @Column(length = 16)
    @NotNull
    private String password;

    @Column(length = 14)
    @NotNull
    private String phone;

    @Column(length = 20)
    @NotNull
    private String avatar;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cart> carts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rate> rates;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<OrderDebt> orderDebts;
}
