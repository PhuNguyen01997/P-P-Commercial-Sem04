package com.example.demo.Entity;

import com.sun.istack.NotNull;

//import javax.persistence.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

@Entity
@Table(name = "Shopusers")
public class User {
    @Id
    private Integer id;
    @OneToOne(mappedBy = "userId")
    private Shop shopId;
    //    @Column(columnDefinition = "nvarchar(MAX)")
    @Column(length = 255)
    @NotNull
    private String email;
    //    @Column(columnDefinition = "nvarchar(MAX)")
    @Column(length = 255)
    @NotNull
    private String userName;
    @Column(length = 16)
    @NotNull
    private String password;
    @Column(length = 14)
    @NotNull
    private String phone;
    //    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    @Column(length = 255)
    private String address;
    @Column(length = 50)
    @NotNull
    private String avatar;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;


    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Cart> carts;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Rate> rates;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<OrderDept> orderDepts;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Order> orders;
}
