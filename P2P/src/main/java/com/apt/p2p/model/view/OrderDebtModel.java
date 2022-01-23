package com.apt.p2p.model.view;

import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDebtModel {
    private Integer id;

    private BigDecimal total;

    private Date isPaid = null;

    private Date time;

    private Date deadlinePayment;

    private User user;

    private List<Order> orders;
}
