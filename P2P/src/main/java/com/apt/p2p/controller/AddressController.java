package com.apt.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {
    @GetMapping("address")
    public String address(){
        return "user/account/address";
    }
}
