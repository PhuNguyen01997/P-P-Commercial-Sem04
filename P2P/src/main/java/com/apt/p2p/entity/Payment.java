package com.apt.p2p.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private String stripeCardId;

    @NotNull
    private String addressRegister;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
    private List<Order> orders;
}
