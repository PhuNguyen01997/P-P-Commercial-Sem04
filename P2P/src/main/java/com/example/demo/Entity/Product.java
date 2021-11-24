package com.example.demo.Entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column
    @NotNull
    private Integer  id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_Id")
    private Category categoryId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_Id")
    private Shop shopId;
//    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    @Column(length = 255)
    private String productName;
    @Column
    @NotNull
    private Double price ;
    @Column(columnDefinition="TEXT")
    @NotNull
    private String image;
    @Column(columnDefinition="TEXT")
    @NotNull
    private String description;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
    private List<Cart> carts;
    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}
