package com.apt.p2p.controller;

import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.model.TestModel;
import com.apt.p2p.service.PaymentService;
import org.aspectj.weaver.ast.Test;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Payment")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("")
    public String create(@RequestBody TestModel paymentModel) {
        return paymentModel.getId().toString();
    }

}