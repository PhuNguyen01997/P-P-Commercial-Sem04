package com.apt.p2p.model.view;

import com.apt.p2p.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private Integer id;

    private Boolean methodPayment;

    private Double total;

    private Double percentPermission;

    private Double debt;

    private Date createdAt;

    private Date updatedAt;

    private UserModel user;

    private List<OrderDetailModel> orderDetails;

    private StatusOrder statusOrder;

    private ShopModel shop;

    private AddressModel address;

    private PaymentModel payment;

//    private OrderDebtModel orderDebt;
}
