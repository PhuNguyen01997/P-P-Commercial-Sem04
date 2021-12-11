package com.apt.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {
    @GetMapping("pay")
    public String payment(){
        return "user/main/payment";
    }

    @GetMapping("card")
    public String card(){
        return "user/account/card";
    }
}
