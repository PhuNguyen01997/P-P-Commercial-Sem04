package com.apt.p2p.entity;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 80)
    @NotNull
    private String name;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;
}
