package com.apt.p2p.controller;

import com.apt.p2p.service.PaymentService;

@RestController
@RequestMapping("/Payment")
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    @GetMapping("/Create")
    public Payment create() {
        return paymentService.create();
    }

}