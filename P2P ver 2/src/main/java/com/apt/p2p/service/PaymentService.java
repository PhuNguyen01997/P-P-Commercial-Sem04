package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;

@Service
public class PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;

    public Payment create() {
        Payment payment = new Payment();
        return payment;
    }
}