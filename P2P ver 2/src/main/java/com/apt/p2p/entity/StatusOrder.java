package com.apt.p2p.entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "StatusOrder")
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(length = 30)
    private String name;

    @OneToMany(mappedBy = "statusOrder", fetch = FetchType.LAZY)
    private List<Order> orders;
}
