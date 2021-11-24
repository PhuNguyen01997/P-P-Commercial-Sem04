package com.example.demo.Entity;



import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Carts")
public class Cart {
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Id")
    private Product productId;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
