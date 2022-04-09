package com.apt.p2p.model.view;

import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.ShopTransaction;
import com.apt.p2p.entityEnum.ShopTransactionStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopTransactionModel {
    private int id;

    private BigDecimal amount;

    private String description;

    private Date date;

    private ShopModel shop;

    private OrderModel order;

    private ShopTransactionStatus status;

    public ShopTransactionModel(ShopTransaction entity) {
        this.id = entity.getId();
        this.amount = entity.getAmount();
        this.description = entity.getDescription();
        this.date = entity.getDate();
        this.status = entity.getStatus();

        this.shop = null;
        this.order = null;
    }
}
