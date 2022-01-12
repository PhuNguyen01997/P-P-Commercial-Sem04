package com.apt.p2p.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "StatusOrder")
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String doneName;

    @OneToMany(mappedBy = "statusOrder", fetch = FetchType.LAZY)
    private List<Order> orders;
}
