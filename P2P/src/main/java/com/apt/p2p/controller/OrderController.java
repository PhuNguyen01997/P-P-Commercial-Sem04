package com.apt.p2p.controller;

import com.apt.p2p.entity.OrderDetail;
import com.apt.p2p.model.view.OrderDetailModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.service.OrderDetailService;
import com.apt.p2p.service.OrderService;
import com.apt.p2p.service.ProductService;
import com.apt.p2p.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("order")
    public String order(Model model) {
        int userId = 3;

        List<OrderModel> orders = orderService.findAllByUserId(userId);

        model.addAttribute("orders", orders);

        return "user/account/order-user";
    }

    @GetMapping("order/{id}")
    public String orderDetail(@PathVariable("id") int id,
                              Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "user/account/order-detail";
    }
}
