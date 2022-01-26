package com.apt.p2p.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String stripeCardId;

    @NotNull
    private String addressRegister;

    @OneToOne(mappedBy = "card", fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

//    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
//    private List<Order> orders;
}
