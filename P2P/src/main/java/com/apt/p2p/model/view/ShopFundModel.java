package com.apt.p2p.model.view;

import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShopFundModel {
    private Integer id;

    private BigDecimal fund;

    private ShopModel shop;

    private List<OrderModel> orders;
}
