package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shopId")
    private Shop shop;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Cart> carts;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Rate> rates;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
