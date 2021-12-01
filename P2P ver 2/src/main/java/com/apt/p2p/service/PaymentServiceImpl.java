package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment create(Payment payment) {
        return null;
    }
}
