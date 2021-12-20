package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PaymentService {
    public PaymentModel create(PaymentModel paymentModel);

    public List<PaymentModel> findAll();

    public List<PaymentModel> findAllByUserId(int id);

    public boolean delete(int id);
}