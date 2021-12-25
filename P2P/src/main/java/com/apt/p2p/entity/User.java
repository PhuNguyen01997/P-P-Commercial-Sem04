package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column (name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private boolean enabled = true;

    @Column(length = 60)
    @NotNull
    private String password;

    @Column(length = 14)
    @NotNull
    private String phone;

    private String avatar;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @OneToMany(mappedBy = "primaryKey.user" ,fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<UserRole> userRoles;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cart> carts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rate> rates;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<OrderDebt> orderDebts;

}
