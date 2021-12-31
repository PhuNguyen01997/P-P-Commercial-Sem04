package com.apt.p2p.controller;

import com.apt.p2p.model.modelview.AddressModel;
import com.apt.p2p.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("address")
    public String index(Model model){
        List<AddressModel> addressList = addressService.findByUserId(1);
        model.addAttribute("addressList", addressList);

        return "user/account/address";
    }

    @PostMapping("address")
    public String create(){
        return "redirect/address";
    }
}
