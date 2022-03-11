package com.apt.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("")
    public String index() {
        return "user/main/index";
    }

    @GetMapping("shop/{shopId}/order/{orderId}")
    public String orderDetailShop(@PathVariable("shopId") int shopId, @PathVariable("orderId") int orderId) {
        return "user/account/order-detail";
    }

    @GetMapping("edit")
    public String userEdit() {
        return "user/account/user-form";
    }

    @GetMapping("identity")
    public String identity() {
        return "user/account/identity";
    }

//    @GetMapping("/shop/{shopId}/product")
//    public String products(@PathVariable("shopId") int shopId) {
//        return "user/account/product";
//    }

//    @GetMapping("/shop/{shopId}/product/create")
//    public String productCreate(@PathVariable("shopId") int shopId) {
//        return "user/account/product-form";
//    }
//
//    @GetMapping("/shop/{shopId}/product/{productId}/edit")
//    public String productEdit(@PathVariable("shopId") int shopId, @PathVariable("shopId") int productId) {
//        return "user/account/product-form";
//    }

//    @GetMapping("shop/create")
//    public String shopCreate() {
//        return "user/account/shop-form";
//    }

//    @GetMapping("shop/{shopId}/edit")
//    public String shopEdit(@PathVariable("shopId") int shopId) {
//        return "user/account/shop-form";
//    }
}
