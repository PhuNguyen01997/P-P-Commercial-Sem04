package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "StatusOrder")
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String doneName;

    @OneToMany(mappedBy = "currentStatus", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<OrderStatusOrder> orderStatusOrders;

    public String getIconUrl(){
        return "imgUrl";
    }
}
