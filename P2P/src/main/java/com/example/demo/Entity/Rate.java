package com.example.demo.Entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "rates")
public class Rate {
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    private User userId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_Id")
    private Shop shopId;
    @Column(columnDefinition="TEXT")
    @NotNull
    private String description;
    @Column
    @NotNull
    private Integer stars;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
