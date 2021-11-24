package com.example.demo.Entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "shops")
public class Shop {
    @Id
    private Integer id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @Column(length = 50)
    @NotNull
    private String logo;
//    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    @Column(length = 255)
    private String address;
    @Column(length = 14)
    @NotNull
    private String phone;
    @Column
    @NotNull
    private Boolean permission;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
    @OneToMany(mappedBy = "shopId", fetch = FetchType.LAZY)
    private List<Product> products;
    @OneToMany(mappedBy = "shopId", fetch = FetchType.LAZY)
    private List<Rate> rates;
    @OneToMany(mappedBy = "shopId", fetch = FetchType.LAZY)
    private List<Order> orders;
}
