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

    private BigDecimal amount;

    private String description;

    private Date date = new Date();

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    private Shop shop;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    @Enumerated(EnumType.STRING)
    private ShopTransactionStatus status;

    public ShopTransaction(Shop shop, Order order) {
        this.shop = shop;
        this.order = order;
        this.amount = order.getTotal();
        this.status = order.getMethodPayment() ? ShopTransactionStatus.SUCCESS : ShopTransactionStatus.WAIT;
        if(order != null){
            this.description = "Khách hàng mua hàng thanh toán";
        }else{
            this.description = "Rút tiền từ cửa hàng vào tài khoản cá nhân";
        }
    }
}