package com.apt.p2p.controller;

import com.apt.p2p.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FundController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/portal/{id}/fund")
    public String portalIndex(Model model, @PathVariable("id") int shopId) {
        model.addAttribute("shop", shopService.findById(shopId));
        return "user/portal/fund";
    }

    @GetMapping("/portal/{id}/fund/withdraw")
    public String portalWithdraw(Model model, @PathVariable("id") int shopId) {
        model.addAttribute("shop", shopService.findById(shopId));
        return "user/portal/fund-withdraw";
    }
}
