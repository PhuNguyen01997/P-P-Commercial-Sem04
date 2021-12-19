package com.apt.p2p.repository;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("SELECT p.shop FROM Payment p WHERE p.id = :id")
    public Shop findShopByPaymentId(@Param("id") Integer id);
}