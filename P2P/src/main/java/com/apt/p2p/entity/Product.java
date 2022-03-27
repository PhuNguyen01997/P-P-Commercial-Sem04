package com.apt.p2p.entity;

import com.apt.p2p.model.view.ProductModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String image;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String description;

    @NotNull
    private int stock = 0;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Product(ProductModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.price = model.getPrice();
        this.description = model.getDescription();
        this.stock = model.getStock();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();

        ObjectMapper mapper = new ObjectMapper();
        try {
            this.image = mapper.writeValueAsString(model.getImage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.shop = null;
        this.category = null;

        this.carts = new ArrayList<>();
        this.rates = new ArrayList<>();
        this.orderDetails = new ArrayList<>();
    }
}
