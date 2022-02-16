package com.apt.p2p.controller;

import com.apt.p2p.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("portal/{id}")
    public String shopDetail(Model model, @PathVariable("id") int shopId) {
        model.addAttribute("shop", shopService.findById(shopId));
        return "user/portal/shop-form";
    }

    @GetMapping("portal/create")
    public String shopCreate(Model model) {
        model.addAttribute("shop", shopService.findById(0));
        return "user/portal/shop-form";
    }
}
