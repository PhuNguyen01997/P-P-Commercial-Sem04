package com.apt.p2p.repository;

import com.apt.p2p.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> { }