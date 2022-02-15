package com.apt.p2p.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ShopController {
    @GetMapping("portal/{id}")
    public String shopDetail(@PathVariable int shopId) {
        return "user/main/shop-detail";
    }
}
