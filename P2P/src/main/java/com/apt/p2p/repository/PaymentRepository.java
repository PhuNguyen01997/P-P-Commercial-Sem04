package com.apt.p2p.repository;

import com.apt.p2p.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> { }