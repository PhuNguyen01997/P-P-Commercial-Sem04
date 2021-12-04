package com.apt.p2p.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OrderDebt")
public class OrderDebt {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Double total;

    private Date isPaid = null;

    @NotNull
    private Date time;

    @NotNull
    private Date deadlinePayment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "orderDebt", fetch = FetchType.LAZY)
    private List<Order> orders;
}
