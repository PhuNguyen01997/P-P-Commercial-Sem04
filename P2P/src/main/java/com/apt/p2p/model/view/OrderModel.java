package com.apt.p2p.model.view;

import com.apt.p2p.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private Integer id;

    private Boolean methodPayment;

    private BigDecimal total;

    private BigDecimal shippingCost;

    private Double percentPermission;

    private Date createdAt;

    private Date updatedAt;

    private UserModel user;

    private List<OrderDetailModel> orderDetails;

    private List<StatusHistory> statusHistories;

    private StatusOrder currentStatus;

    private ShopModel shop;

    private AddressModel address;

    private CardModel payment;

    public BigDecimal calTotal() {
        BigDecimal result = orderDetails.stream().map(OrderDetailModel::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return result;
    }

    public BigDecimal getShopRealReceiveTotal(){
        return total.subtract(total.multiply(BigDecimal.valueOf(percentPermission)));
    }

    public OrderModel(Order entity) {
        this.id = entity.getId();
        this.methodPayment = entity.getMethodPayment();
        this.total = entity.getTotal();
        this.shippingCost = entity.getShippingCost();
        this.percentPermission = entity.getPercentPermission();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.statusHistories = entity.getStatusHistories();
        this.currentStatus = entity.getCurrentStatus();

        this.payment = null;
        this.shop = null;
        this.user = null;
        this.address = null;

        this.orderDetails = new ArrayList<>();
        this.statusHistories = new ArrayList<>();
    }
}
