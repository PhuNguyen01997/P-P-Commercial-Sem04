package com.apt.p2p.controller;

import com.apt.p2p.common.modelMapper.OrderMapper;
import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.StatusHistory;
import com.apt.p2p.model.form.FilterOrder;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.view.*;
import com.apt.p2p.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    private OrderDetailService orderDetailService;
    @Autowired
    private RateService rateService;
    @Autowired
    private StatusOrderService statusOrderService;
    @Autowired
    private StatusHistoryService statusHistoryService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderMapper orderMapper;

//    @GetMapping("order")
//    public String index(Model model) {
//        UserModel user = userService.getCurrentUser();
//        model.addAttribute("user", user);
//
//        List<OrderModel> orders = orderService.findAllByUserId(user.getId());
//        model.addAttribute("orders", orders);
//
//        return "user/account/order-user";
//    }

    @GetMapping("order")
    public String index(Model model, @RequestParam(required = false, name = "page") Integer pageNumber) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        PagiSortModel pagiSortModel = new PagiSortModel();
        pagiSortModel.setPage(pageNumber == null ? 0 : pageNumber);
        pagiSortModel.setSize(5);

        Page<Order> orderPages = orderService.findAllByUserId(user.getId(), pagiSortModel);
        List<OrderModel> orderModels = orderPages.stream().map(e -> {
            OrderModel orderModel = orderMapper.orderEntityToModel(e);
            orderModel.setOrderDetails(orderDetailService.findAllByOrderId(orderModel.getId()));
            return orderModel;
        }).collect(Collectors.toList());
        model.addAttribute("orders", orderModels);

        ResponsePagiView pagiView = new ResponsePagiView(pagiSortModel.getPage(), orderPages.getTotalPages());
        model.addAttribute("pagiView", pagiView);

        return "user/account/order-user";
    }

    @GetMapping("order/{id}")
    public String orderDetail(@PathVariable("id") int id,
                              Model model) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        OrderModel order = orderService.findById(id);
        HashMap<Integer, StatusHistory> statusMapStatusHistory = statusHistoryService.findStatusOrderMapStatusHistoryByOrderId(order.getId());
        model.addAttribute("order", order);
        model.addAttribute("statusList", statusOrderService.findAll());
        model.addAttribute("statusMapStatusHistory", statusMapStatusHistory);

        List<RateModel> rates = rateService.findAllByOrderIdAndUserId(order.getId(), user.getId());
        if(order.getCurrentStatus().getId() == 5 && rates.size() == 0){
            model.addAttribute("canRate", true);
        }

        return "user/account/order-detail";
    }

    @GetMapping("portal/order")
    public String portalIndex(Model model) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        model.addAttribute("shop", user.getShop() != null ? shopService.findByUserId(user.getId()) : null);

        return "user/portal/order";
    }

    @GetMapping("api/order/{id}")
    @ResponseBody
    public OrderModel apiGetOrderById(@PathVariable("id") int orderId){
        OrderModel orderModel =  orderService.findById(orderId);
        return orderModel;
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
        List<OrderModel> result = orderService.findAllByShopIdWithFilter(shopId, input);

        return result;
    }
}
