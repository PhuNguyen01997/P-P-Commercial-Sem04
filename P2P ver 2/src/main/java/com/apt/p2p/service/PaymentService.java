package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface PaymentService {
    public Payment create(Payment payment);
}