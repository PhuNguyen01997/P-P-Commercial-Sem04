package com.apt.p2p.controller;

import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Payment")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/Create")
    public String create(@RequestBody PaymentModel paymentModel) {
        return paymentModel.getFullname();
    }

}