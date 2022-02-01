package com.apt.p2p.controller;

import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private ShopService shopService;

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
        int userId = 3;
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("shop", shopService.findByOrderId(id));
        return "user/account/order-detail";
    }
}
