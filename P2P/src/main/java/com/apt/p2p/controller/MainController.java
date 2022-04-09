package com.apt.p2p.controller;

import com.apt.p2p.entity.User;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    UsersDetailServiceImpl usersDetailService;
    @GetMapping("")
    public String index() {
        User user = usersDetailService.getCurrentUser();
        if (user != null) {
            System.out.println("==================================");
            System.out.println(user.getUsername());
        } else {
            System.out.println("null");

        }
        return "user/main/index";
    }

    @GetMapping("shop/{id}")
    public String shopDetail(@PathVariable int id) {
        return "user/main/shop-detail";
    }

    @GetMapping("product/{productSlug}")
    public String productDetail(@PathVariable String productSlug) {
        return "user/main/product-detail";
    }

    @GetMapping("order")
    public String order() {
        return "user/account/order-user";
    }

    @GetMapping("shop/{shopId}/order")
    public String orderShop(@PathVariable("shopId") int shopId) {
        return "user/account/order-shop";
    }

    @GetMapping("order/{id}")
    public String orderDetail(@PathVariable("id") int id) {
        return "user/account/order-detail";
    }

    @GetMapping("shop/{shopId}/order/{orderId}")
    public String orderDetailShop(@PathVariable("shopId") int shopId, @PathVariable("orderId") int orderId) {
        return "user/account/order-detail";
    }

    @GetMapping("edit")
    public String userEdit() {
        return "user/account/user-form";
    }

    @GetMapping("cart")
    public String cart() {
        return "user/main/cart";
    }

    @GetMapping("identity")
    public String identity() {
        return "user/account/identity";
    }

    @GetMapping("/shop/{shopId}/product")
    public String products(@PathVariable("shopId") int shopId) {
        return "user/account/product";
    }

    @GetMapping("/shop/{shopId}/product/create")
    public String productCreate(@PathVariable("shopId") int shopId) {
        return "user/account/product-form";
    }

    @GetMapping("/shop/{shopId}/product/{productId}/edit")
    public String productEdit(@PathVariable("shopId") int shopId, @PathVariable("shopId") int productId) {
        return "user/account/product-form";
    }

    @GetMapping("shop/create")
    public String shopCreate() {
        return "user/account/shop-form";
    }

    @GetMapping("shop/{shopId}/edit")
    public String shopEdit(@PathVariable("shopId") int shopId) {
        return "user/account/shop-form";
    }
}
