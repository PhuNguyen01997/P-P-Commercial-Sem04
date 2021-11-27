package com.apt.p2p.entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String image;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String description;

    private Date createdAt;

    private Date updatedAt;

    //    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_Id")
//    private Category categoryId;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shopId")
    private Shop shop;

    // Not neccessary because we don't have business logic to find product -> carts
//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private List<Cart> carts;
//    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
//    private List<OrderDetail> orderDetails;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
