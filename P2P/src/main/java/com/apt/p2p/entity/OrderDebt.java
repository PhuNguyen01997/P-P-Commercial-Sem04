package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OrderDebt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDebt {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal total;

    private Date isPaid = null;

    @NotNull
    @Max(12)
    @Min(1)
    private int month;

    @NotNull
    @Max(3000)
    @Min(2021)
    private int year;

    @NotNull
    private Date deadlinePayment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "orderDebt", fetch = FetchType.LAZY)
    private List<Order> orders;

    public OrderDebt(BigDecimal total) {
        LocalDate localDate = LocalDate.now();
        this.month = localDate.getMonthValue();
        this.year = localDate.getYear();

        LocalDate lastDayofMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        lastDayofMonth = lastDayofMonth.plusDays(11);
        this.deadlinePayment = Date.from(lastDayofMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.total = total;
    }
}
