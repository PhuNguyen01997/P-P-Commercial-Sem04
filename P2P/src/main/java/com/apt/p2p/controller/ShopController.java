package com.apt.p2p.controller;

import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;

    @GetMapping("portal/{id}")
    public String shopDetail(Model model, @PathVariable("id") int shopId) {
        int userId = 2;
        ShopModel shopModel = shopService.findById(shopId);

        if (shopModel == null) {
            model.addAttribute("shop", new ShopModel());
        }
        model.addAttribute("shop", shopModel);
        model.addAttribute("user", userService.findById(userId));
        return "user/portal/shop-form";
    }

    @PostMapping("/portal/{id}")
    public String updateShop(Model model, @PathVariable("id") int shopId, @ModelAttribute("shop") ShopModel shop) {

        return "redirect:/portal/" + shopId;
    }

    @GetMapping("portal/create")
    public String shopCreate(Model model) {
        int userId = 2;
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("shop", new ShopModel());
        return "user/portal/shop-form";
    }
}
