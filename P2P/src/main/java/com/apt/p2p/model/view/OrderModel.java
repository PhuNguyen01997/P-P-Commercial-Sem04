package com.apt.p2p.model.view;

import com.apt.p2p.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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

    @JsonIgnore
    private UserModel user;

    @JsonIgnore
    private List<OrderDetailModel> orderDetails;

    @JsonIgnore
    private List<OrderStatusOrder> orderStatusOrders;

    @JsonIgnore
    private StatusOrder currentStatus;

    @JsonIgnore
    private ShopModel shop;

    @JsonIgnore
    private AddressModel address;

    @JsonIgnore
    private CardModel payment;

    @JsonIgnore
    private ShopFundModel shopFund;

    public BigDecimal calTotal(){
        BigDecimal result = orderDetails.stream().map(OrderDetailModel::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return result;
    }
}
