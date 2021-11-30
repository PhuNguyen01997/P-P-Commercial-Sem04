package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment create(Payment payment) {
        repository.save(payment);
        return payment;
    }
}