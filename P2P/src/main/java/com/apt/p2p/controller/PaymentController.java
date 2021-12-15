package com.apt.p2p.controller;

import com.apt.p2p.entity.CardType;
import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService service;

    @GetMapping("pay")
    public String payment(){
        return "user/main/payment";
    }

    @GetMapping("card")
    public String card(Model model){
        model.addAttribute("card", new Payment());
        model.addAttribute("paymentTypes", Arrays.asList(CardType.values()));
        return "user/account/card";
    }

    @PostMapping("card")
    public String create(PaymentModel paymentModel){
        service.create(paymentModel);
        return "user/main/index";
    }
}
