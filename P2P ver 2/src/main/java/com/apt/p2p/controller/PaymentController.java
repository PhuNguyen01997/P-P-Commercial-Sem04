package com.apt.p2p.controller;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Payment")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("")
    public String create(@Valid @RequestBody PaymentModel paymentModel) {
        paymentModel.setUserId(1);
        service.create(paymentModel);
        return "boo";
    }
}