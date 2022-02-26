package com.apt.p2p.entity;

import com.apt.p2p.model.view.UserModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column (name = "userId")
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

    private String stripeCustomerId;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cart> carts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rate> rates;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;
}
