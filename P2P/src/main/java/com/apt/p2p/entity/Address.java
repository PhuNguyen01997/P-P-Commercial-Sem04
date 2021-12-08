package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(length = 100)
    private String number;

    @NotNull
    private String ward;

    @NotNull
    private  String district;

    @NotNull
    private String province;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Shop shop;

    public Address(String number, String ward, String district, String province) {
        this.number = number;
        this.ward = ward;
        this.district = district;
        this.province = province;
    }
}
