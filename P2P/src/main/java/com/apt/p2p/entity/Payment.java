package com.apt.p2p.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String fullname;

    @NotNull
    @Column(length = 20)
    private String number;

    @NotNull
    private String type;

    @NotNull
    private Date due;

    @NotNull
    @Column(length = 10)
    private String cvv;

    @NotNull
    private String addressRegister;

    @NotNull
    @Column(length = 10)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private Shop shop;
}
