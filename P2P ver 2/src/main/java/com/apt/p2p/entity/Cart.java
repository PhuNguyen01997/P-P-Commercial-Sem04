package com.apt.p2p.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Date createdAt;

    private Date updatedAt;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_Id")
//    private User userId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;
}
