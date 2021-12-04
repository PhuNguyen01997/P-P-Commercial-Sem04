package com.apt.p2p.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String fullname;

    @NotNull
    @Column(length = 16)
    private String number;

    @NotNull
    private  Integer type;

    @NotNull
    private Date due;

    @NotNull
    private Integer cvv;

    @NotNull
    private String addressRegister;

    @NotNull
    private Integer postalCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private Shop shop;
}
