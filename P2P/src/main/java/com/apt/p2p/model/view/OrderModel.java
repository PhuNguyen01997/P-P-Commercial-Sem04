package com.apt.p2p.model.view;

import com.apt.p2p.entity.*;
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

    private Double percentPermission;

    private Date createdAt;

    private Date updatedAt;

    private UserModel user;

    private List<OrderDetailModel> orderDetails;

    private StatusOrder statusOrder;

    private ShopModel shop;

    private AddressModel address;

    private CardModel payment;

    private ShopFundModel shopFund;
}
