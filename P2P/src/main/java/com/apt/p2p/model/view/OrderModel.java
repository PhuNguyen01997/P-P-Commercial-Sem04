package com.apt.p2p.model.view;

import com.apt.p2p.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"shop", "addresses", "orders"})
    private UserModel user;

    @JsonIgnoreProperties({"order", "product"})
    private List<OrderDetailModel> orderDetails;

    @JsonIgnoreProperties({"order"})
    private List<OrderStatusOrder> orderStatusOrders;

    @JsonIgnoreProperties({"orders"})
    private StatusOrder currentStatus;

    @JsonIgnoreProperties({"orders", "user"})
    private ShopModel shop;

    @JsonIgnoreProperties("user")
    private AddressModel address;

    @JsonIgnoreProperties
    private CardModel payment;

    @JsonIgnoreProperties("user")
    private ShopFundModel shopFund;

    public BigDecimal calTotal() {
        BigDecimal result = orderDetails.stream().map(OrderDetailModel::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return result;
    }
}
