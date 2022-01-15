package com.apt.p2p.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String ownName;

    @NotNull
    @Column(length = 14)
    private String ownPhone;

    @NotNull
    @Column(length = 100)
    private String number;

    @NotNull
    private String ward;

    @NotNull
    private int wardId;

    @NotNull
    private  String district;

    @NotNull
    private int districtId;

    @NotNull
    private String province;

    @NotNull
    private int provinceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private List<Order> orders;
}
