package com.example.demo.Entity;

import com.sun.istack.NotNull;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "orderdepts")
public class OrderDept {
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    private User userId;
    @Column
    @NotNull
    private Double total;
    @Column
    private Date isPaid;
    @Column
    @NotNull
    private Date time;
    @Column
    @NotNull
    private Date deadlinePayment;

    @OneToMany(mappedBy = "orderDeptId", fetch = FetchType.LAZY)
    private List<Order> orders;
}
