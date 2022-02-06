package com.apt.p2p.controller;

import com.apt.p2p.model.form.CalShippingForm;
import com.apt.p2p.model.form.FilterOrder;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private ShopService shopService;

    @GetMapping("order")
    public String index(Model model) {
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

    @GetMapping("/portal/{id}/order")
    public String portalIndex(Model model, @PathVariable("id") int shopId) {
        model.addAttribute("shop", shopService.findById(shopId));

        return "user/portal/order";
    }

    @PostMapping("/api/order/{id}")
    @ResponseBody
    public List<OrderModel> apiIndex(@PathVariable("id") int shopId, @RequestBody FilterOrder input) {
        LocalDateTime ldtMaxDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(input.getMaxDate().getTime()), ZoneOffset.UTC);
        ldtMaxDate = ldtMaxDate.plusDays(1);
        Date dMaxDate = Date.from(ldtMaxDate.toInstant(ZoneOffset.UTC));
        input.setMaxDate(dMaxDate);

        List<OrderModel> result = orderService.findAllByShopIdWithFilter(shopId, input);

        return result;
    }
}
