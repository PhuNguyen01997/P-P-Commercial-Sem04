package com.apt.p2p.controller;

import com.apt.p2p.entity.StatusHistory;
import com.apt.p2p.model.form.FilterOrder;
import com.apt.p2p.model.view.OrderDetailModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StatusOrderService statusOrderService;
    @Autowired
    private StatusHistoryService statusHistoryService;
    @Autowired
    private ShopService shopService;

    @GetMapping("order")
    public String index(Model model) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        List<OrderModel> orders = orderService.findAllByUserId(user.getId());
        model.addAttribute("orders", orders);

        return "user/account/order-user";
    }

    @GetMapping("order/{id}")
    public String orderDetail(@PathVariable("id") int id,
                              Model model) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        OrderModel order = orderService.findById(id);
        HashMap<Integer, StatusHistory> statusMapStatusHistory = statusHistoryService.findStatusOrderMapStatusHistoryByOrderId(order.getId());
        Date date = statusMapStatusHistory.get(1).getDate();
        model.addAttribute("order", order);
        model.addAttribute("statusList", statusOrderService.findAll());
        model.addAttribute("statusMapStatusHistory", statusHistoryService.findStatusOrderMapStatusHistoryByOrderId(order.getId()));

        return "user/account/order-detail";
    }

    @GetMapping("portal/order")
    public String portalIndex(Model model) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        model.addAttribute("shop", user.getShop() != null ? shopService.findByUserId(user.getId()) : null);

        return "user/portal/order";
    }

    @PostMapping("api/order/{id}")
    @ResponseBody
    public boolean updateStatus(@PathVariable("id") int orderId, @RequestParam("statusId") int statusId) {
        boolean result = orderService.updateStatus(orderId, statusId);
        return result;
    }

    @PostMapping("/api/shop/{id}/order")
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
