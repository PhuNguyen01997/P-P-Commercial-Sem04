package com.apt.p2p.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(length = 100)
    private String number;

    @NotNull
    private String ward;

    @NotNull
    private  String district;

    @NotNull
    private String province;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Shop shop;
}