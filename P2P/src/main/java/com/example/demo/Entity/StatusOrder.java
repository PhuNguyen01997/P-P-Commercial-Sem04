package com.example.demo.Entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "statusorders")
public class StatusOrder {
    @Id
    private Integer id;
    @Column(length = 100)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "statusOrderId", fetch = FetchType.LAZY)
    private List<Order> orders;
}
