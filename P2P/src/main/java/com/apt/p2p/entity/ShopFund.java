package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ShopFund")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal fund;

//    private Date isPaid = null;

//    @NotNull
//    @Max(12)
//    @Min(1)
//    private int month;
//
//    @NotNull
//    @Max(3000)
//    @Min(2021)
//    private int year;

//    @NotNull
//    private Date deadlinePayment;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    private Shop shop;

    @OneToMany(mappedBy = "shopFund", fetch = FetchType.LAZY)
    private List<Order> orders;

    public ShopFund(Shop shop, List<Order> orders) {
//        LocalDate localDate = LocalDate.now();
//        this.month = localDate.getMonthValue();
//        this.year = localDate.getYear();

//        LocalDate lastDayofMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
//        lastDayofMonth = lastDayofMonth.plusDays(11);
//        this.deadlinePayment = Date.from(lastDayofMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.shop = shop;
        this.orders = orders;
        this.fund = orders.stream().map(o -> {
            BigDecimal minus = o.getTotal().multiply(BigDecimal.valueOf(o.getPercentPermission()));
            return o.getTotal().subtract(minus);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public ShopFund(Shop shop, BigDecimal fund) {
        this.fund = fund;
        this.shop = shop;
        this.orders = new ArrayList<>();
    }
}
