package com.apt.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @GetMapping("")
    public String index(){
        return "user/main/index";
    }

    @GetMapping("shop/{id}")
    public String shopDetail(@PathVariable int id){
        return "user/main/shop-detail";
    }

    @GetMapping("product/{productSlug}")
    public String productDetail(@PathVariable String productSlug){
        return "user/main/product-detail";
    }

    @GetMapping("order")
    public String order(){
        return "user/account/order-user";
    }

    @GetMapping("shop/order")
    public String orderShop(){
        return "user/account/order-shop";
    }

    @GetMapping("order/{id}")
    public String orderDetail(@PathVariable int id){
        return "user/account/order-detail";
    }

    @GetMapping("shop/order/{id}")
    public String orderDetailShop(@PathVariable int id){
        return "user/account/order-detail";
    }

    @GetMapping("edit")
    public String userEdit(){
        return "user/account/user-form";
    }

    @GetMapping("cart")
    public String cart(){
        return "user/account/cart";
    }

    @GetMapping("identity")
    public String identity(){
        return "user/account/identity";
    }

    @GetMapping("product")
    public String products(){
        return "user/account/product";
    }

    @GetMapping("product/create")
    public String productCreate(){
        return "user/account/product-form";
    }

    @GetMapping("product/{id}/edit")
    public String productEdit(@PathVariable int id){
        return "user/account/product-form";
    }

    @GetMapping("shop/create")
    public String shopCreate(){
        return "user/account/shop-form";
    }

    @GetMapping("shop/{id}/edit")
    public String shopEdit(@PathVariable  int id){
        return "user/account/shop-form";
    }
}
