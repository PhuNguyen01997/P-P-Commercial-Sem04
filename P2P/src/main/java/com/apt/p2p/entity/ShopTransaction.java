package com.apt.p2p.entity;

import com.apt.p2p.entityEnum.ShopTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ShopTransaction")
public class ShopTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String description;

    @NotNull
    private Date date = new Date();

    @Enumerated(EnumType.ORDINAL)
    private ShopTransactionStatus status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    private Shop shop;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    public ShopTransaction(Shop shop, Order order) {
        this.shop = shop;
        this.order = order;
        BigDecimal totalAfterSubtractPermission = order.getTotal().subtract(order.getTotal().multiply(BigDecimal.valueOf(0.05)));
        this.amount = totalAfterSubtractPermission;
//        if (!order.getMethodPayment() && order.getCurrentStatus().getId() != 6) {
//            this.status = ShopTransactionStatus.WAIT;
//        } else {
            this.status = ShopTransactionStatus.SUCCESS;
//        }
        this.description = "Khách hàng mua hàng thanh toán";
    }

    public ShopTransaction(Shop shop, BigDecimal amount) {
        this.shop = shop;
        this.order = null;
        this.amount = amount;
        this.status = ShopTransactionStatus.WAIT;
        this.description = "Rút tiền từ cửa hàng vào tài khoản cá nhân";
    }

    public ShopTransaction(Shop shop, BigDecimal amount, ShopTransactionStatus status) {
        this(shop, amount);
        this.status = status;
    }
}