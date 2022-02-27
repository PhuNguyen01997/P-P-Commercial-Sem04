package com.apt.p2p.controller;

import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.AddressService;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @GetMapping("portal")
    public String shopDetail(Model model) {
        int userId = 3;
        UserModel user = userService.findById(userId);

        model.addAttribute("shop", user.getShop() != null ? shopService.findByUserId(user.getId()) : new ShopModel());
        model.addAttribute("user", user);
        return "user/portal/shop-form";
    }

    @PostMapping("portal")
    public String updateShop(Model model, @RequestParam("teAddress") int addressId, @Valid @ModelAttribute("shop") ShopModel shop, BindingResult result) {
        int userId = 3;
        UserModel user = userService.findById(userId);
        AddressModel address = addressService.findById(addressId);
        shop.setAddress(address);
        shop.setUser(user);

        if(result.hasErrors()){
            model.addAttribute("shop", shop);
            model.addAttribute("user", user);
            return "user/portal/shop-form";
        }

        ShopModel shopModel = shopService.createOrUpdate(shop);
        return "redirect:/portal";
    }

//    @GetMapping("portal/create")
//    public String shopCreate(Model model) {
//        int userId = 2;
//        model.addAttribute("user", userService.findById(userId));
//        model.addAttribute("shop", new ShopModel());
//        return "user/portal/shop-form";
//    }
}
