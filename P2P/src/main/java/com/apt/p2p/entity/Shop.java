package com.apt.p2p.entity;

import com.apt.p2p.model.view.ShopModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String logo;

    @NotNull
    private String name;

    @Column(length = 14)
    @NotNull
    private String phone;

    @NotNull
    private BigDecimal fund = BigDecimal.ZERO;

    private Boolean permission = false;

    @NotNull
    private String description;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private Address address;

    @NotNull
    private String stripeCardId;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<ShopTransaction> shopTransactions;

    public Shop(ShopModel model) {
        this.id = model.getId();
        this.logo = model.getLogo();
        this.name = model.getName();
        this.phone = model.getPhone();
        this.fund = model.getFund();
        this.permission = model.getPermission();
        this.description = model.getDescription();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();

        this.user = null;
        this.products = null;
        this.address = null;
        this.stripeCardId = null;
        this.orders = null;
        this.shopTransactions = null;
    }
}
