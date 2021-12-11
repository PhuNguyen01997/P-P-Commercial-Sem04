package com.apt.p2p.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AddressController {
    @GetMapping("address")
    public String address(){
        return "user/account/address";
    }
}
