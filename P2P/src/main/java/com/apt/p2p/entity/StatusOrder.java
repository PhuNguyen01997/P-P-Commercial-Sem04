package com.apt.p2p.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @OneToMany(mappedBy = "currentStatus", fetch = FetchType.LAZY)
//    private List<Order> orders;

//    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
//    private List<OrderStatusOrder> orderStatusOrders;

    public String getIconUrl(){
        String url = "";
        switch (this.id){
            case 1: {
                url = "far fa-file-alt";
                break;
            }
            case 2: {
                url = "fas fa-dollar-sign";
                break;
            }
            case 3: {
                url = "fas fa-dolly-flatbed";
                break;
            }
            case 4: {
                url = "fas fa-truck";
                break;
            }
            case 5: {
                url = "far fa-star";
                break;
            }
        }
        return url;
    }
}
